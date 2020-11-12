package io.leia.builder.params;

import com.google.gson.annotations.SerializedName;
import io.leia.client.model.Document;
import io.leia.client.model.FormatTypes;
import io.leia.client.model.TransformTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ConditionalJobRuleJobParams {
    public static final String SERIALIZED_NAME_DOCUMENT_IDS = "document_ids";
    @SerializedName(SERIALIZED_NAME_DOCUMENT_IDS)
    private List<String> documentIds = new ArrayList<>();

    public static final String SERIALIZED_NAME_JOB_TYPE = "job_type";
    @SerializedName(SERIALIZED_NAME_JOB_TYPE)
    private String jobType;

    public static final String SERIALIZED_NAME_TAG = "tag";
    @SerializedName(SERIALIZED_NAME_TAG)
    private String tag;

    public static final String SERIALIZED_NAME_PAGE_RANGE = "page_range";
    @SerializedName(SERIALIZED_NAME_PAGE_RANGE)
    private String pageRange;

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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPageRange() {
        return pageRange;
    }

    public void setPageRange(String pageRange) {
        this.pageRange = pageRange;
    }

    public static class Transform extends ConditionalJobRuleJobParams {
        public static final String SERIALIZED_NAME_TRANSFORM_PARAMS = "transform_params";
        @SerializedName(SERIALIZED_NAME_TRANSFORM_PARAMS)
        private HashMap<String, Object> transformParams = new HashMap<>();

        public static final String SERIALIZED_OUTPUT_TYPE = "output_type";
        @SerializedName(SERIALIZED_OUTPUT_TYPE)
        private TransformTypes outputType;

        public HashMap<String, Object> getTransformParams() {
            return transformParams;
        }

        public void addTransformParams(String key, Object value) {
            this.transformParams.put(key, value);
        }

        public void setTransformParams(HashMap<String, Object> transformParams) {
            this.transformParams = transformParams;
        }

        public TransformTypes getOutputType() {
            return outputType;
        }

        public void setOutputType(TransformTypes outputType) {
            this.outputType = outputType;
        }
    }
    public static class Model extends ConditionalJobRuleJobParams {
        public static final String SERIALIZED_NAME_MODEL_PARAMS = "model_params";
        @SerializedName(SERIALIZED_NAME_MODEL_PARAMS)
        private HashMap<String, Object> modelParams = new HashMap<>();

        public static final String SERIALIZED_NAME_MODEL_ID = "model_id";
        @SerializedName(SERIALIZED_NAME_MODEL_ID)
        private String modelId;

        public static final String SERIALIZED_NAME_FORMAT_TYPE = "format_type";
        @SerializedName(SERIALIZED_NAME_FORMAT_TYPE)
        private FormatTypes formatType;

        public HashMap<String, Object> getModelParams() {
            return modelParams;
        }

        public void addModelParams(String key, Object value) {
            this.modelParams.put(key, value);
        }

        public void setModelParams(HashMap<String, Object> modelParams) {
            this.modelParams = modelParams;
        }

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public FormatTypes getFormatType() {
            return formatType;
        }

        public void setFormatType(FormatTypes formatType) {
            this.formatType = formatType;
        }
    }

}

