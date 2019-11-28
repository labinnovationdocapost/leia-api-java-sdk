package io.leia.builder;

import io.leia.builder.params.TransformDocumentParams;
import io.leia.client.model.Document;
import io.leia.client.model.Job;
import io.leia.client.model.TransformBody;
import io.leia.client.model.TransformTypes;

import java.util.Arrays;
import java.util.List;

public final class TransformDocumentParamsBuilder {
    private TransformDocumentParams transformDocumentParams;

    private TransformDocumentParamsBuilder() {
        transformDocumentParams = new TransformDocumentParams();
    }

    public static TransformDocumentParamsBuilder create(String[] documentIds, TransformTypes outputType) {
        return new TransformDocumentParamsBuilder().withDocumentIds(Arrays.asList(documentIds)).withOutputType(outputType);
    }
    public static TransformDocumentParamsBuilder create(String documentId, TransformTypes outputType) {
        return new TransformDocumentParamsBuilder().addDocumentId(documentId).withOutputType(outputType);
    }
    public static TransformDocumentParamsBuilder create(Document[] documents, TransformTypes outputType) {
        return new TransformDocumentParamsBuilder().withDocuments(Arrays.asList(documents)).withOutputType(outputType);
    }
    public static TransformDocumentParamsBuilder create(Document document, TransformTypes outputType) {
        return new TransformDocumentParamsBuilder().addDocument(document).withOutputType(outputType);
    }

    public TransformDocumentParamsBuilder withDocumentIds(List<String> documentIds) {
        transformDocumentParams.setDocumentIds(documentIds);
        return this;
    }
    public TransformDocumentParamsBuilder withDocuments(List<Document> documents) {
        transformDocumentParams.setDocuments(documents);
        return this;
    }

    public TransformDocumentParamsBuilder addDocument(Document document) {
        transformDocumentParams.addDocument(document);
        return this;
    }
    public TransformDocumentParamsBuilder addDocumentId(String documentId) {
        transformDocumentParams.addDocumentId(documentId);
        return this;
    }

    public TransformDocumentParamsBuilder withOutputType(TransformTypes outputType) {
        transformDocumentParams.setOutputType(outputType);
        return this;
    }

    public TransformDocumentParamsBuilder withInputTag(String inputTag) {
        transformDocumentParams.setInputTag(inputTag);
        return this;
    }

    public TransformDocumentParamsBuilder withOutputTag(String outputTag) {
        transformDocumentParams.setOutputTag(outputTag);
        return this;
    }

    public TransformDocumentParamsBuilder executeAfterId(String executeAfterId) {
        transformDocumentParams.setExecuteAfterId(executeAfterId);
        return this;
    }
    public TransformDocumentParamsBuilder executeAfter(Job executeAfter) {
        transformDocumentParams.setExecuteAfter(executeAfter);
        return this;
    }

    public TransformDocumentParamsBuilder withPageRange(String pageRange) {
        transformDocumentParams.setPageRange(pageRange);
        return this;
    }

    public TransformDocumentParamsBuilder withCallbackUrl(String callbackUrl) {
        transformDocumentParams.setCallbackUrl(callbackUrl);
        return this;
    }

    public TransformDocumentParamsBuilder withTransformParams(Object transformParams) {
        transformDocumentParams.setTransformParams(transformParams);
        return this;
    }

    public TransformDocumentParamsBuilder withTransformBody(TransformBody transformBody) {
        transformDocumentParams.setTransformBody(transformBody);
        return this;
    }

    public TransformDocumentParams build() {
        return transformDocumentParams;
    }
}
