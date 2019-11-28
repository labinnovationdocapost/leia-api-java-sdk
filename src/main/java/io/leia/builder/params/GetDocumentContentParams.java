package io.leia.builder.params;

import io.leia.client.model.Document;

import java.lang.reflect.ParameterizedType;

public class GetDocumentContentParams {
    private String documentId;
    private Integer maxSize;
    private Integer jpegCompression;
    private Class returnType;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setDocument(Document document) {
        this.documentId = document.getId();
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getJpegCompression() {
        return jpegCompression;
    }

    public void setJpegCompression(Integer jpegCompression) {
        this.jpegCompression = jpegCompression;
    }

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }
}
