package io.leia.builder.params;

import io.leia.client.model.Document;
import io.leia.client.model.Job;
import io.leia.client.model.TransformBody;
import io.leia.client.model.TransformTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TransformDocumentParams {
    private List<String> documentIds;
    private TransformTypes outputType;
    private String inputTag;
    private String outputTag;
    private String executeAfterId;
    private String pageRange;
    private String callbackUrl;
    private Object transformParams;
    private TransformBody transformBody;
    private HashMap<String, String> callbackHeaders;

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public void setDocuments(List<Document> documents) {
        this.documentIds = documents.stream().map(Document::getId).collect(Collectors.toList());
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

    public TransformTypes getOutputType() {
        return outputType;
    }

    public void setOutputType(TransformTypes outputType) {
        this.outputType = outputType;
    }

    public String getInputTag() {
        return inputTag;
    }

    public void setInputTag(String inputTag) {
        this.inputTag = inputTag;
    }

    public String getOutputTag() {
        return outputTag;
    }

    public void setOutputTag(String outputTag) {
        this.outputTag = outputTag;
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

    public Object getTransformParams() {
        return transformParams;
    }

    public void setTransformParams(Object transformParams) {
        this.transformParams = transformParams;
    }

    public TransformBody getTransformBody() {
        return transformBody;
    }

    public void setTransformBody(TransformBody transformBody) {
        this.transformBody = transformBody;
    }

    public HashMap<String, String> getCallbackHeaders() {
        return callbackHeaders;
    }

    public void setCallbackHeaders(HashMap<String, String> callbackHeaders) {
        this.callbackHeaders = callbackHeaders;
    }
}
