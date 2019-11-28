package io.leia.builder;

import io.leia.builder.params.ModifyDocumentParams;
import io.leia.client.model.Document;

public final class ModifyDocumentParamsBuilder {
    private ModifyDocumentParams modifyDocumentParams;

    private ModifyDocumentParamsBuilder() {
        modifyDocumentParams = new ModifyDocumentParams();
    }

    public static ModifyDocumentParamsBuilder create(String documentId) {
        return new ModifyDocumentParamsBuilder().withDocumentId(documentId);
    }
    public static ModifyDocumentParamsBuilder create(Document document) {
        return new ModifyDocumentParamsBuilder().withDocument(document);
    }

    public ModifyDocumentParamsBuilder withDocumentId(String documentId) {
        modifyDocumentParams.setDocumentId(documentId);
        return this;
    }

    public ModifyDocumentParamsBuilder withDocument(Document document) {
        modifyDocumentParams.setDocument(document);
        return this;
    }

    public ModifyDocumentParamsBuilder withFilename(String filename) {
        modifyDocumentParams.setFilename(filename);
        return this;
    }

    public ModifyDocumentParamsBuilder withRotationAngle(Integer rotationAngle) {
        modifyDocumentParams.setRotationAngle(rotationAngle);
        return this;
    }

    public ModifyDocumentParamsBuilder withTtl(Integer ttl) {
        modifyDocumentParams.setTtl(ttl);
        return this;
    }

    public ModifyDocumentParams build() {
        return modifyDocumentParams;
    }
}
