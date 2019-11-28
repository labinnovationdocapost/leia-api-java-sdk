package io.leia.builder.params;

import io.leia.client.model.ApplyBody;
import io.leia.client.model.Document;
import io.leia.client.model.FormatTypes;
import io.leia.client.model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InferedDocumentParams {
    private String modelId = null;
    private List<String> documentIds = null;
    private String tag = null;
    private FormatTypes formatType = null;
    private String executeAfterId = null;
    private String pageRange = null;
    private String callbackUrl = null;
    private Object modelParams = null;
    private ApplyBody applyBody = null;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String model) {
        this.modelId = model;
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }
    public void setDocuments(List<Document> documentIds) {
        this.documentIds = documentIds.stream().map(Document::getId).collect(Collectors.toList());
    }

    public void addDocument(Document document) {
        if(this.documentIds == null)
            this.documentIds = new ArrayList<>();
        this.documentIds.add(document.getId());
    }

    public void addDocumentId(String documentId) {
        if(this.documentIds == null)
            this.documentIds = new ArrayList<>();
        this.documentIds.add(documentId);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public FormatTypes getFormatType() {
        return formatType;
    }

    public void setFormatType(FormatTypes formatType) {
        this.formatType = formatType;
    }

    public String getExecuteAfterId() {
        return executeAfterId;
    }

    public void setExecuteAfterId(String executeAfterId) {
        this.executeAfterId = executeAfterId;
    }

    public void setExecuteAfter(Job executeAfterId) {
        this.executeAfterId = executeAfterId.getId();
    }

    public String getPageRange() {
        return pageRange;
    }

    public void setPageRange(String pageRange) {
        this.pageRange = pageRange;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Object getModelParams() {
        return modelParams;
    }

    public void setModelParams(Object modelParams) {
        this.modelParams = modelParams;
    }

    public ApplyBody getApplyBody() {
        return applyBody;
    }

    public void setApplyBody(ApplyBody applyBody) {
        this.applyBody = applyBody;
    }
}
