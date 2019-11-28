package io.leia.builder;

import io.leia.builder.params.InferedDocumentParams;
import io.leia.client.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InferedDocumentParamsBuilder {
    private InferedDocumentParams inferedDocumentParams;

    private InferedDocumentParamsBuilder() {
        inferedDocumentParams = new InferedDocumentParams();
    }

    public static InferedDocumentParamsBuilder create(String model, Document[] documents) {
        return new InferedDocumentParamsBuilder().withModelId(model).withDocuments(Arrays.asList(documents));
    }
    public static InferedDocumentParamsBuilder create(String model, String[] documents) {
        return new InferedDocumentParamsBuilder().withModelId(model).withDocumentIds(Arrays.asList(documents));
    }
    public static InferedDocumentParamsBuilder create(String model, Document document) {
        return new InferedDocumentParamsBuilder().withModelId(model).addDocument(document);
    }
    public static InferedDocumentParamsBuilder create(String model, String document) {
        return new InferedDocumentParamsBuilder().withModelId(model).addDocumentId(document);
    }


    public static InferedDocumentParamsBuilder create(Model model, Document[] documents) {
        InferedDocumentParamsBuilder inferedDocumentParamsBuilder = new InferedDocumentParamsBuilder();
        inferedDocumentParamsBuilder.withModel(model);
        inferedDocumentParamsBuilder.withDocuments(Arrays.asList(documents));
        return inferedDocumentParamsBuilder;
    }
    public static InferedDocumentParamsBuilder create(Model model, String[] documents) {
        InferedDocumentParamsBuilder inferedDocumentParamsBuilder = new InferedDocumentParamsBuilder();
        inferedDocumentParamsBuilder.withModel(model);
        inferedDocumentParamsBuilder.withDocumentIds(Arrays.asList(documents));
        return inferedDocumentParamsBuilder;
    }
    public static InferedDocumentParamsBuilder create(Model model, Document document) {
        InferedDocumentParamsBuilder inferedDocumentParamsBuilder = new InferedDocumentParamsBuilder();
        inferedDocumentParamsBuilder.withModel(model);
        inferedDocumentParamsBuilder.addDocument(document);
        return inferedDocumentParamsBuilder;
    }
    public static InferedDocumentParamsBuilder create(Model model, String document) {
        InferedDocumentParamsBuilder inferedDocumentParamsBuilder = new InferedDocumentParamsBuilder();
        inferedDocumentParamsBuilder.withModel(model);
        inferedDocumentParamsBuilder.addDocumentId(document);
        return inferedDocumentParamsBuilder;
    }

    public InferedDocumentParamsBuilder withModelId(String model) {
        inferedDocumentParams.setModelId(model);
        return this;
    }
    public InferedDocumentParamsBuilder withModel(Model model) {
        inferedDocumentParams.setModelId(model.getId());
        return this;
    }

    public InferedDocumentParamsBuilder withDocumentIds(List<String> documents) {
        inferedDocumentParams.setDocumentIds(documents);
        return this;
    }
    public InferedDocumentParamsBuilder withDocuments(List<Document> documents) {
        inferedDocumentParams.setDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()));
        return this;
    }
    public InferedDocumentParamsBuilder addDocument(Document document) {
        inferedDocumentParams.addDocument(document);
        return this;
    }
    public InferedDocumentParamsBuilder addDocumentId(String documentId) {
        inferedDocumentParams.addDocumentId(documentId);
        return this;
    }

    public InferedDocumentParamsBuilder withTag(String tag) {
        inferedDocumentParams.setTag(tag);
        return this;
    }

    public InferedDocumentParamsBuilder withFormatType(FormatTypes formatType) {
        inferedDocumentParams.setFormatType(formatType);
        return this;
    }

    public InferedDocumentParamsBuilder executeAfter(Job executeAfterId) {
        inferedDocumentParams.setExecuteAfter(executeAfterId);
        return this;
    }
    public InferedDocumentParamsBuilder executeAfter(String executeAfterId) {
        inferedDocumentParams.setExecuteAfterId(executeAfterId);
        return this;
    }

    public InferedDocumentParamsBuilder withPageRange(String pageRange) {
        inferedDocumentParams.setPageRange(pageRange);
        return this;
    }

    public InferedDocumentParamsBuilder withCallbackUrl(String callbackUrl) {
        inferedDocumentParams.setCallbackUrl(callbackUrl);
        return this;
    }

    public InferedDocumentParamsBuilder withModelParams(Object modelParams) {
        inferedDocumentParams.setModelParams(modelParams);
        return this;
    }

    public InferedDocumentParamsBuilder withApplyBody(ApplyBody applyBody) {
        inferedDocumentParams.setApplyBody(applyBody);
        return this;
    }

    public InferedDocumentParams build() {
        return inferedDocumentParams;
    }
}
