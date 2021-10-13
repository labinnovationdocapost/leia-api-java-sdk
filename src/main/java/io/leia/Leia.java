package io.leia;

import com.google.common.io.ByteStreams;
import com.google.gson.reflect.TypeToken;
import io.leia.builder.AddModelParamsBuilder;
import io.leia.builder.params.*;
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.api.*;
import io.leia.client.model.*;
import io.leia.custom.client.tools.JobTools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Leia {
    private ApiClient apiClient = null;
    private String apiKey = null;

    private ReentrantLock counterLock = new ReentrantLock(true);

    public LoginToken getToken() {
        try{
            counterLock.lock();
            return token;
        }
        finally {
            counterLock.unlock();
        }
    }

    public void setToken(LoginToken token) {
        this.token = token;
    }

    private LoginToken token = null;
    private LocalDateTime lastError = null;

    private int error = 0;

    private ApplicationApi applicationApi;
    private DocumentApi documentApi;
    private JobApi jobApi;
    private ModelApi modelApi;
    private ModelAdminApi modelAdminApi;
    private WorkerApi workerApi;
    private HealthApi healthApi;

    //region constructor
    public Leia(String apiKey) {
        this.apiKey = apiKey;
        apiClient = new ApiClient();
        init(apiClient);
    }
    public Leia(String serverUrl, String apiKey) {
        this.apiKey = apiKey;
        apiClient = new ApiClient();
        apiClient.setBasePath(serverUrl);
        init(apiClient);
    }
    public Leia(ApiClient apiClient, String apiKey) {
        this.apiKey = apiKey;
        init(apiClient);
    }
    //endregion

    private void init(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.applicationApi = new ApplicationApi(this.apiClient);
        this.documentApi = new DocumentApi(this.apiClient);
        this.jobApi = new JobApi(this.apiClient);
        this.modelApi = new ModelApi(this.apiClient);
        this.modelAdminApi = new ModelAdminApi(this.apiClient);
        this.workerApi = new WorkerApi(this.apiClient);
        this.healthApi = new HealthApi(this.apiClient);
    }

    private LeiaException createLeiaException(ApiException e) {
        if(e.getCode() == 400)
            return createLeiaException(e, "Client error, parameters invalid");
        if(e.getCode() == 401)
            return createLeiaException(e, "Token invalid or missing");
        if(e.getCode() == 403)
            return createLeiaException(e, "Api key invalid");
        if(e.getCode() == 404)
            return createLeiaException(e, "Client error, resource not found");

        return createLeiaException(e, "Unknow error");
    }
    private LeiaException createLeiaException(Exception e, String message) {
        return new LeiaException(message, e);
    }

    public LoginToken login() throws LeiaException {
        return login(false);
    }
    public LoginToken login(Boolean force) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        if(!force) {
            if(token != null)
                return token;
        }
        try {
            counterLock.lock();
            setToken(applicationApi.loginApplicationPost(new LoginBody().apiKey(apiKey)));
            counterLock.unlock();
            return getToken();
        } catch (ApiException e) {
            counterLock.unlock();
            if(tryCorrectError(e, datetime)) {
                return login();
            }
            throw createLeiaException(e);
        }
    }

    private synchronized Boolean tryCorrectError(ApiException e, LocalDateTime errorTime) throws LeiaException {
        if(lastError != null && errorTime.isBefore(lastError)) {
            return true;
        }
        if(error > 0)
            throw createLeiaException(e, "To many error, abandon");

        lastError = LocalDateTime.now();

        error++;

        if(e.getCode() == 401)
        {
            LoginToken token = login(true);
            if(token == null)
                throw createLeiaException(e, "Token invalid or missing");
            return true;
        }
        else
        {
            throw createLeiaException(e);
        }
    }

    private void verifyToken() throws LeiaException {
        if(token == null) {
            login();
        }
    }


    //region Document
    //region getDocument
    public Document getDocument(Document document) throws LeiaException {
        return getDocument(document.getId());
    }
    public Document getDocument(String documentId) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            return documentApi.getDocument(token.getToken(), documentId);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return getDocument(documentId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region getDocumentContent
    public <T> T getDocumentContent(GetDocumentContentParams params) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            InputStream output = documentApi.getDocumentContents(token.getToken(), params.getDocumentId(), params.getMaxSize(), params.getJpegCompression());
            if(params.getReturnType() == String.class){
                return (T)new String(ByteStreams.toByteArray(output), StandardCharsets.UTF_8);
            }
            if(params.getReturnType() == byte[].class || params.getReturnType() == Byte[].class){
                return (T)ByteStreams.toByteArray(output);
            }

            throw new LeiaException(String.format("Output type not supported : %s",params.getReturnType().getName()), null);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return getDocumentContent(params);
            }
            throw createLeiaException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LeiaException("Unexpected error", e);
        }
    }
    //endregion

    //region createDocument
    public Document createDocument(String filename, byte[] body) throws LeiaException {
        return createDocument(filename, body, null, null, null);
    }
    public Document createDocument(String filename, byte[] body, Boolean b64) throws LeiaException {
        return createDocument(filename, body, b64,null, null);

    }
    public Document createDocument(String filename, byte[] body, Boolean b64, Integer ttl) throws LeiaException {
        return createDocument(filename, body, b64, ttl,null);

    }
    public Document createDocument(String filename, byte[] body, Boolean b64, Integer ttl, List<String> tags) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            return documentApi.createDocument(token.getToken(), filename, body, b64, ttl, tags);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return createDocument(filename, body, b64, ttl, tags);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region getDocumentsTags
    List<String> getDocumentsTags() throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            return documentApi.getDocumentsTags(token.getToken());
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return getDocumentsTags();
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region transformDocument
    public Job transformDocuments(TransformDocumentParams params) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return documentApi.transformDocumentAsync(token.getToken(), params.getDocumentIds(), params.getOutputType(), params.getInputTag(), params.getOutputTag(), params.getExecuteAfterId(), params.getPageRange(), params.getCallbackUrl(), params.getTransformParams(), false, params.getTransformBody());
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return transformDocuments(params);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region deleteDocument
    void deleteDocument(Document document) throws LeiaException {
        deleteDocument(document.getId());
    }
    void deleteDocument(String documentId) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            documentApi.deleteDocument(token.getToken(), documentId);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                deleteDocument(documentId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region modifyDocument
    void modifyDocument(ModifyDocumentParams params) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            documentApi.editDocument(token.getToken(), params.getDocumentId(), params.getFilename(), params.getRotationAngle(), params.getTtl());
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                modifyDocument(params);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region untagDocument
    void untagDocument(String documentId, String tag) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            documentApi.untagDocument(token.getToken(), documentId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                untagDocument(documentId, tag);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region tagDocument
    public void tagDocument(String documentId, String tag) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();

        try {
            documentApi.tagDocument(token.getToken(), documentId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                tagDocument(documentId, tag);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region conditional job
    public Job conditionalJob(ConditionalJobParams params) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return jobApi.createConditionalJob(token.getToken(), params.getExecuteAfterId(), params.getCallbackUrl(), false, params);
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println(e.getResponseBody());
            if(e.getCode() == 404) {
                return null;
            }
            if(tryCorrectError(e,datetime)){
                return conditionalJob(params);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region job
    //region getJobResult
    public Job getJobResult(Job job) throws LeiaException {
        return getJobResult(job.getId());
    }

    public Job getJobResult(String jobId) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return jobApi.getJob(token.getToken(), jobId);
        } catch (ApiException e) {
            e.printStackTrace();
            if(tryCorrectError(e, datetime)){
                return getJobResult(jobId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region awaitJob
    public Job awaitJob(Job job) throws LeiaException {
        return awaitJob(job.getId(), 30000);
    }
    public Job awaitJob(Job job, int pullSpeed) throws LeiaException {
        return awaitJob(job.getId(), pullSpeed);
    }
    public Job awaitJob(String jobId) throws LeiaException {
        return awaitJob(jobId, 30000);
    }
    public Job awaitJob(String jobId, int pullSpeed) throws LeiaException {
        Job job;
        while(true){
            job = getJobResult(jobId);
            if(JobTools.isJobFinish(job))
            {
                return job;
            }
            try {
                Thread.sleep(pullSpeed);
            } catch (InterruptedException e) {
                throw new LeiaException("Pulling failed", e);
            }
        }
    }
    //endregion
    //endregion

    //region model
    //region getModels
    public List<Model> getModels(GetModelsParams params) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return modelApi.getModels(token.getToken(), params.getModelId(), params.getModelType(), params.getName() , params.getShortName(), params.getDescription() ,params.getInputTypes() ,params.getTags(), params.getCreatedAfter(), params.getCreatedBefore(), params.getOnlyMime(), params.getSort(), params.getOffset(), params.getLimit());
        } catch (ApiException e) {
            if(e.getCode() == 404) {
                return new ArrayList<>();
            }
            if(tryCorrectError(e,datetime)){
                return getModels(params);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region getModel
    public Model getModel(Model model) throws LeiaException {
        return getModel(model.getId());
    }
    public Model getModel(String modelId) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return modelApi.getModel(token.getToken(), modelId);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return getModel(modelId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region inferred
    public Job inferredDocuments(InferedDocumentParams params) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return modelApi.applyModelAsync(token.getToken(), params.getModelId(), params.getDocumentIds(), params.getTag(), params.getFormatType(), params.getExecuteAfterId(), params.getPageRange(), params.getCallbackUrl(), params.getModelParams(), false, params.getApplyBody());
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return inferredDocuments(params);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region tagModel
    public Model tagModel(Model model, String tag) throws LeiaException {
        return tagModel(model.getId(), tag);
    }
    public Model tagModel(String modelId, String tag) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return modelApi.tagModel(token.getToken(), modelId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return tagModel(modelId, tag);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region untagModel
    public void untagModel(Model model, String tag) throws LeiaException {
        untagModel(model.getId(), tag);
    }
    public void untagModel(String modelId, String tag) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            modelApi.untagModel(token.getToken(), modelId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                untagModel(modelId, tag);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region worker
    //region getWorkers
    public List<Worker> getWorkers() throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return workerApi.getWorkers(token.getToken());
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return getWorkers();
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region worker
    public Worker getWorker(String jobType) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return workerApi.getWorker(token.getToken(), jobType);
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return getWorker(jobType);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region health
    //region healthCheck
    public void healthCheck() throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            healthApi.healthCheck();
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                healthCheck();
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region addModel (Unfinished)
    public Model addModel(AddModelParams addModelParams) throws LeiaException {
        LocalDateTime datetime = LocalDateTime.now();
        verifyToken();
        try {
            return modelAdminApi.adminCreateModel(token.getToken(), addModelParams.getApplicationId(), addModelParams.getName(), addModelParams.getFile(), addModelParams.getShortname(), addModelParams.getDescription(), addModelParams.getTtl(), addModelParams.getAllowedApplicationIds(), addModelParams.getAllowAllApplications(), addModelParams.getTags());
        } catch (ApiException e) {
            if(tryCorrectError(e, datetime)){
                return addModel(addModelParams);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
}
