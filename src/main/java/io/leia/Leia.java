package io.leia;

import com.google.common.io.ByteStreams;
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.api.*;
import io.leia.client.model.*;
import io.leia.custom.client.tools.JobTools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Leia {
    /**
     * The class Leia is NOT thread safe
     */
    private ApiClient apiClient = null;
    private String apiKey = null;
    private LoginToken token = null;

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
        System.out.println("login");
        try {
            return token = applicationApi.loginApplication(apiKey);
        } catch (ApiException e) {
            if(tryCorrectError(e)) {
                return login();
            }
            throw createLeiaException(e);
        }
    }

    private Boolean tryCorrectError(ApiException e) throws LeiaException {
        if(error > 0)
            throw createLeiaException(e, "To many error, abandon");

        error++;

        if(e.getCode() == 401)
        {
            LoginToken token = login();
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
            System.out.println("not log");
            login();
        }
    }


    //region Document
    //region getDocument
    public Document getDocument(Document document) throws LeiaException {
        return getDocument(document.getId());
    }
    public Document getDocument(String documentId) throws LeiaException {
        System.out.println("createDocument");
        verifyToken();

        try {
            return documentApi.getDocument(token.getToken(), documentId);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return getDocument(documentId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region getDocumentContent
    public byte[] getDocumentContent(Document document) throws LeiaException {
        return getDocumentContent(document.getId(), null, null, byte[].class);
    }
    public <T> T getDocumentContent(Document document, Class<T> clazz) throws LeiaException {
        return getDocumentContent(document.getId(), null, null, clazz);
    }
    public <T> T getDocumentContent(Document document, Integer maxSize, Class<T> clazz) throws LeiaException {
        return getDocumentContent(document.getId(), maxSize, null, clazz);
    }
    public <T> T getDocumentContent(Document document, Integer maxSize, Integer jpegCompression, Class<T> clazz) throws LeiaException {
        return getDocumentContent(document.getId(), maxSize, jpegCompression, clazz);
    }
    public byte[] getDocumentContent(String documentId) throws LeiaException {
        return getDocumentContent(documentId, null, null, byte[].class);
    }
    public <T> T getDocumentContent(String documentId, Class<T> clazz) throws LeiaException {
        return getDocumentContent(documentId, null, null, clazz);
    }
    public <T> T getDocumentContent(String documentId, Integer maxSize, Class<T> clazz) throws LeiaException {
        return getDocumentContent(documentId, maxSize, null, clazz);
    }
    public <T> T getDocumentContent(String documentId, Integer maxSize, Integer jpegCompression, Class<T> clazz) throws LeiaException {
        System.out.println("createDocument");
        verifyToken();

        try {
            InputStream output = documentApi.getDocumentContents(token.getToken(), documentId, maxSize, jpegCompression);
            if(clazz.isAssignableFrom(String.class)){
                return (T)new String(ByteStreams.toByteArray(output), StandardCharsets.UTF_8);
            }
            if(clazz.isAssignableFrom(byte[].class)){
                return (T)ByteStreams.toByteArray(output);
            }

            throw new LeiaException(String.format("Output type not supported : %s",clazz.getName()), null);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return getDocumentContent(documentId,maxSize, jpegCompression, clazz);
            }
            throw createLeiaException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LeiaException("Unexpected error", e);
        }
    }
    //endregion

    //region createDocument
    public Document createDocument(String filename, InputStream body) throws LeiaException {
        return createDocument(filename, body, null, null, null);
    }
    public Document createDocument(String filename, InputStream body, Boolean b64) throws LeiaException {
        return createDocument(filename, body, b64,null, null);

    }
    public Document createDocument(String filename, InputStream body, Boolean b64, Integer ttl) throws LeiaException {
        return createDocument(filename, body, b64, ttl,null);

    }
    public Document createDocument(String filename, InputStream body, Boolean b64, Integer ttl, List<String> tags) throws LeiaException {
        System.out.println("createDocument");
        verifyToken();

        try {
            return documentApi.createDocument(token.getToken(), filename, body, b64, ttl, tags);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return createDocument(filename, body, b64, ttl, tags);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region getDocumentsTags
    List<String> getDocumentsTags() throws LeiaException {
        System.out.println("getDocumentsTags");
        verifyToken();

        try {
            return documentApi.getDocumentsTags(token.getToken());
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return getDocumentsTags();
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region transformDocument
    //region transformDocuments
    public Job transformDocuments(List<Document> documents, TransformTypes outputType) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, null, null, (String)null, null, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, null, (String)null, null, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, (String)null, null, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId, null, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId, String callbackUrl) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId, callbackUrl, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId, String callbackUrl, Object transformParams) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId, String callbackUrl, Object transformParams, TransformBody transformBody) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, transformBody);
    }

    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId.getId(), null, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId, String callbackUrl) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId.getId(), callbackUrl, null, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId, String callbackUrl, Object transformParams) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId.getId(), callbackUrl, transformParams, null);
    }
    public Job transformDocuments(List<Document> documents, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId, String callbackUrl, Object transformParams, TransformBody transformBody) throws LeiaException {
        return transformDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()), outputType, inputTag, outputTag, executeAfterId.getId(), callbackUrl, transformParams, transformBody);
    }
    //endregion

    //region transformDocumentIds
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId.getId(), null, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId, String callbackUrl) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId.getId(), callbackUrl, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId, String callbackUrl, Object transformParams) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId.getId(), callbackUrl, transformParams, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, Job executeAfterId, String callbackUrl, Object transformParams, TransformBody transformBody) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId.getId(), callbackUrl, transformParams, transformBody);
    }

    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, null, null, (String)null, null, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, null, (String)null, null, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, (String)null, null, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId, null, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId, String callbackUrl) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId, callbackUrl, null, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId, String callbackUrl, Object transformParams) throws LeiaException {
        return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, null);
    }
    public Job transformDocumentIds(List<String> documentIds, TransformTypes outputType, String inputTag, String outputTag, String executeAfterId, String callbackUrl, Object transformParams, TransformBody transformBody) throws LeiaException {
        System.out.println("transformDocumentId");
        verifyToken();
        try {
            return documentApi.transformDocumentAsync(token.getToken(), documentIds, outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, transformBody);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return transformDocumentIds(documentIds, outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, transformBody);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region deleteDocument
    void deleteDocument(Document document) throws LeiaException {
        deleteDocument(document.getId());
    }
    void deleteDocument(String documentId) throws LeiaException {
        System.out.println("deleteDocument");
        verifyToken();

        try {
            documentApi.deleteDocument(token.getToken(), documentId);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                deleteDocument(documentId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region modifyDocument
    void modifyDocument(Model document) throws LeiaException {
        modifyDocument(document.getId(), null, null, null);
    }
    void modifyDocument(String documentId) throws LeiaException {
        modifyDocument(documentId, null, null, null);
    }
    void modifyDocument(Model document, String filename) throws LeiaException {
        modifyDocument(document.getId(), filename, null, null);
    }
    void modifyDocument(String documentId, String filename) throws LeiaException {
        modifyDocument(documentId, filename, null, null);
    }
    void modifyDocument(Model document, String filename, Integer rotationAngle) throws LeiaException {
        modifyDocument(document.getId(), filename, rotationAngle, null);
    }
    void modifyDocument(String documentId, String filename, Integer rotationAngle) throws LeiaException {
        modifyDocument(documentId, filename, rotationAngle, null);
    }
    void modifyDocument(Model document, String filename, Integer rotationAngle, Integer ttl) throws LeiaException {
        modifyDocument(document.getId(), filename, rotationAngle, ttl);
    }
    void modifyDocument(String documentId, String filename, Integer rotationAngle, Integer ttl) throws LeiaException {
        System.out.println("modifyDocument");
        verifyToken();

        try {
            documentApi.editDocument(token.getToken(), documentId, filename, rotationAngle, ttl);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                modifyDocument(documentId, filename, rotationAngle, ttl);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region untagDocument
    void untagDocument(String documentId, String tag) throws LeiaException {
        System.out.println("untagDocument");
        verifyToken();

        try {
            documentApi.untagDocument(token.getToken(), documentId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                untagDocument(documentId, tag);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region tagDocument
    public void tagDocument(String documentId, String tag) throws LeiaException {
        System.out.println("createDocument");
        verifyToken();

        try {
            documentApi.tagDocument(token.getToken(), documentId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                tagDocument(documentId, tag);
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
        System.out.println("getJobResult");
        verifyToken();
        try {

            return jobApi.getJob(token.getToken(), jobId);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return getJobResult(jobId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region awaitJob
    public Job awaitJob(Job job) throws LeiaException {
        return awaitJob(job.getId(), 1000);
    }
    public Job awaitJob(Job job, int pullSpeed) throws LeiaException {
        return awaitJob(job.getId());
    }
    public Job awaitJob(String jobId) throws LeiaException {
        return awaitJob(jobId, 1000);
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
    public List<Model> getModels() throws LeiaException {
        return getModels(null, null, null ,null ,null ,null, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId) throws LeiaException {
        return getModels(modelId, null, null ,null ,null ,null, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType) throws LeiaException {
        return getModels(modelId, modelType, null ,null ,null ,null, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name) throws LeiaException {
        return getModels(modelId, modelType, name ,null ,null ,null, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,null ,null, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,null, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,tags, null, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags, OffsetDateTime createdAfter) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, null, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags, OffsetDateTime createdAfter, OffsetDateTime createdBefore) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, createdBefore, null, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags, OffsetDateTime createdAfter, OffsetDateTime createdBefore, Boolean onlyMime) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, createdBefore, onlyMime, null, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags, OffsetDateTime createdAfter, OffsetDateTime createdBefore, Boolean onlyMime, String sort) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, createdBefore, onlyMime, sort, null, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags, OffsetDateTime createdAfter, OffsetDateTime createdBefore, Boolean onlyMime, String sort, Integer offset) throws LeiaException {
        return getModels(modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, createdBefore, onlyMime, sort, offset, null);
    }
    public List<Model> getModels(String modelId, ModelTypes modelType, String name, String description, List<ModelInputTypes> inputTypes, List<String> tags, OffsetDateTime createdAfter, OffsetDateTime createdBefore, Boolean onlyMime, String sort, Integer offset, Integer limit) throws LeiaException {
        System.out.println("getModels");
        verifyToken();
        try {
            return modelApi.getModels(token.getToken(), modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, createdBefore, onlyMime, sort, offset, limit);
        } catch (ApiException e) {
            if(e.getCode() == 404) {
                return new ArrayList<>();
            }
            if(tryCorrectError(e)){
                return getModels(modelId, modelType, name ,description ,inputTypes ,tags, createdAfter, createdBefore, onlyMime, sort, offset, limit);
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
        System.out.println("getModel");
        verifyToken();
        try {
            return modelApi.getModel(token.getToken(), modelId);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return getModel(modelId);
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region inferred
    //region inferredDocuments
    public Job inferredDocuments(String modelId, List<Document> documents) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()) , null, null, (String)null, null, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, null, (String)null, null, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, (String)null, null, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), null, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId, null, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), callbackUrl, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId, callbackUrl, null, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId, callbackUrl, modelParams, null);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, applyBody);
    }
    public Job inferredDocuments(String modelId, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(modelId, documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId,callbackUrl, modelParams, applyBody);
    }



    public Job inferredDocuments(Model model, List<Document> documents) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()) , null, null, (String)null, null, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, null, (String)null, null, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, (String)null, null, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), null, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId, null, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), callbackUrl, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId, callbackUrl, null, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId, callbackUrl, modelParams, null);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, applyBody);
    }
    public Job inferredDocuments(Model model, List<Document> documents, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(model.getId(), documents.stream().map(x->x.getId()).collect(Collectors.toList()), tag, formatType, executeAfterId,callbackUrl, modelParams, applyBody);
    }
    //endregion

    //region inferredDocumentIds
    public Job inferredDocumentIds(Model model, List<String> documentIds) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, null, null, (String)null, null, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, null, (String)null, null, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, (String)null, null, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId.getId(), null, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId, null, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId.getId(), callbackUrl, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId, callbackUrl, null, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, null);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, applyBody);
    }
    public Job inferredDocumentIds(Model model, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(model.getId(), documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody);
    }

    public Job inferredDocumentIds(String modelId, List<String> documentIds) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, null, null, (String)null, null, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, null, (String)null, null, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, (String)null, null, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId.getId(), null, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId, null, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId.getId(), callbackUrl, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, null, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, null);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, Job executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId.getId(), callbackUrl, modelParams, applyBody);
    }
    public Job inferredDocumentIds(String modelId, List<String> documentIds, String tag, FormatTypes formatType, String executeAfterId, String callbackUrl, Object modelParams, ApplyBody applyBody) throws LeiaException {
        System.out.println("inferred");
        verifyToken();
        try {
            return modelApi.applyModelAsync(token.getToken(), modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return inferredDocumentIds(modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region tagModel
    public Model tagModel(Model model, String tag) throws LeiaException {
        return tagModel(model.getId(), tag);
    }
    public Model tagModel(String modelId, String tag) throws LeiaException {
        System.out.println("tagModel");
        verifyToken();
        try {
            return modelApi.tagModel(token.getToken(), modelId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
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
        System.out.println("untagModel");
        verifyToken();
        try {
            modelApi.untagModel(token.getToken(), modelId, tag);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
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
        System.out.println("getWorkers");
        verifyToken();
        try {
            return workerApi.getWorkers(token.getToken());
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return getWorkers();
            }
            throw createLeiaException(e);
        }
    }
    //endregion

    //region worker
    public Worker getWorker(String jobType) throws LeiaException {
        System.out.println("getWorker");
        verifyToken();
        try {
            return workerApi.getWorker(token.getToken(), jobType);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
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
        System.out.println("healthCheck");
        verifyToken();
        try {
            healthApi.healthCheck();
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                healthCheck();
            }
            throw createLeiaException(e);
        }
    }
    //endregion
    //endregion

    //region addModel (Unfinished)
    public Model addModel(String applicationId, String name, InputStream file, String description, Integer ttl, List<String> allowedApplicationIds, Boolean allowAllApplications, List<String> tags) throws LeiaException {
        System.out.println("addModel");
        verifyToken();
        try {
            return modelAdminApi.adminCreateModel(token.getToken(), applicationId, name, file, description, ttl, allowedApplicationIds, allowAllApplications, tags);
        } catch (ApiException e) {
            if(tryCorrectError(e)){
                return addModel(applicationId, name, file, description, ttl, allowedApplicationIds, allowAllApplications, tags);
            }
            throw createLeiaException(e);
        }
    }
    //endregion
}
