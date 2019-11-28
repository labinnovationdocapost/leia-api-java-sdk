package io.leia.builder;

import io.leia.builder.params.GetDocumentContentParams;
import io.leia.client.model.Document;

public final class GetDocumentContentParamsBuilder {
    private GetDocumentContentParams getDocumentContentParams;

    private GetDocumentContentParamsBuilder() {
        getDocumentContentParams = new GetDocumentContentParams();
    }

    public static GetDocumentContentParamsBuilder create(String documentId) {
        return new GetDocumentContentParamsBuilder().withDocumentId(documentId);
    }
    public static GetDocumentContentParamsBuilder create(Document document) {
        return new GetDocumentContentParamsBuilder().withDocument(document);
    }

    public GetDocumentContentParamsBuilder withDocumentId(String documentId) {
        getDocumentContentParams.setDocumentId(documentId);
        return this;
    }

    public GetDocumentContentParamsBuilder withDocument(Document document) {
        getDocumentContentParams.setDocument(document);
        return this;
    }

    public GetDocumentContentParamsBuilder withMaxSize(Integer maxSize) {
        getDocumentContentParams.setMaxSize(maxSize);
        return this;
    }

    public GetDocumentContentParamsBuilder withJpegCompression(Integer jpegCompression) {
        getDocumentContentParams.setJpegCompression(jpegCompression);
        return this;
    }

    public GetDocumentContentParamsBuilder returnAs(Class returnType) {
        getDocumentContentParams.setReturnType(returnType);
        return this;
    }

    public GetDocumentContentParams build() {
        return getDocumentContentParams;
    }
}
