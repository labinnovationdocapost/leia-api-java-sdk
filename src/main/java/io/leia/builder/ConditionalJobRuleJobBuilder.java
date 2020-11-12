package io.leia.builder;

import io.leia.builder.params.ConditionalJobRuleJobParams;
import io.leia.builder.params.ConditionalJobRuleParams;
import io.leia.client.model.Document;
import io.leia.client.model.FormatTypes;
import io.leia.client.model.TransformTypes;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ConditionalJobRuleJobBuilder {
    public static class Transform {

        private ConditionalJobRuleJobParams.Transform conditionalJobRuleJobParams;

        public Transform withDocumentIds(List<String> documents) {
            conditionalJobRuleJobParams.setDocumentIds(documents);
            return this;
        }
        public Transform withDocuments(List<Document> documents) {
            conditionalJobRuleJobParams.setDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()));
            return this;
        }
        public Transform addDocument(Document document) {
            conditionalJobRuleJobParams.addDocument(document);
            return this;
        }
        public Transform addDocumentId(String documentId) {
            conditionalJobRuleJobParams.addDocumentId(documentId);
            return this;
        }

        private Transform withJobType(String jobType) {
            conditionalJobRuleJobParams.setJobType(jobType);
            return this;
        }

        public Transform withTag(String tag) {
            conditionalJobRuleJobParams.setTag(tag);
            return this;
        }

        public Transform addTransformParams(String key, Object value) {
            conditionalJobRuleJobParams.addTransformParams(key, value);
            return this;
        }

        private Transform withOutputType(TransformTypes outputType) {
            conditionalJobRuleJobParams.setOutputType(outputType);
            return this;
        }

        public Transform withPageRange(String pageRange) {
            conditionalJobRuleJobParams.setPageRange(pageRange);
            return this;
        }

        public static Transform create(TransformTypes transformType) {
            return new Transform().withJobType("transform").withOutputType(transformType);
        }
        public static Transform createPdfToImage(String transformType) {
            return new Transform().withJobType("transform").withOutputType(TransformTypes.IMAGE);
        }
        public static Transform createImageToText() {
            return new Transform().withJobType("transform").withOutputType(TransformTypes.TEXT);
        }
        public static Transform createImageToTextTree() {
            return new Transform().withJobType("transform").withOutputType(TransformTypes.TEXT_TREE);
        }
        private Transform() {
            conditionalJobRuleJobParams = new ConditionalJobRuleJobParams.Transform();
        }
        public ConditionalJobRuleJobParams build() {
            return conditionalJobRuleJobParams;
        }
    }
    public static class Model {

        private ConditionalJobRuleJobParams.Model conditionalJobRuleJobParams;

        public Model withDocumentIds(List<String> documents) {
            conditionalJobRuleJobParams.setDocumentIds(documents);
            return this;
        }
        public Model withDocuments(List<Document> documents) {
            conditionalJobRuleJobParams.setDocumentIds(documents.stream().map(Document::getId).collect(Collectors.toList()));
            return this;
        }
        public Model addDocument(Document document) {
            conditionalJobRuleJobParams.addDocument(document);
            return this;
        }
        public Model addDocumentId(String documentId) {
            conditionalJobRuleJobParams.addDocumentId(documentId);
            return this;
        }

        private Model withJobType(String jobType) {
            conditionalJobRuleJobParams.setJobType(jobType);
            return this;
        }

        public Model withTag(String tag) {
            conditionalJobRuleJobParams.setTag(tag);
            return this;
        }

        public Model withPageRange(String pageRange) {
            conditionalJobRuleJobParams.setPageRange(pageRange);
            return this;
        }

        public Model addModelParams(String key, Object value) {
            conditionalJobRuleJobParams.addModelParams(key, value);
            return this;
        }
        public Model withFormatType(FormatTypes formatType) {
            conditionalJobRuleJobParams.setFormatType(formatType);
            return this;
        }

        private Model withModelId(String modelId) {
            conditionalJobRuleJobParams.setModelId(modelId);
            return this;
        }

        public static Model create(String modelId) {
            return new Model().withModelId(modelId).withJobType("apply_model");
        }
        public static Model create(io.leia.client.model.Model model) {
            return new Model().withModelId(model.getId()).withJobType("apply_model");
        }
        private Model() {
            conditionalJobRuleJobParams = new ConditionalJobRuleJobParams.Model();
        }
        public ConditionalJobRuleJobParams build() {
            return conditionalJobRuleJobParams;
        }
    }
}
