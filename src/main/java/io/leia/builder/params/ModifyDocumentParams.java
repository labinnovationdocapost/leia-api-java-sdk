package io.leia.builder.params;

import io.leia.client.model.Document;

public class ModifyDocumentParams {
    private String documentId = null;
    private String filename = null;
    private Integer rotationAngle = null;
    private Integer ttl = null;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setDocument(Document document) {
        this.documentId = document.getId();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
