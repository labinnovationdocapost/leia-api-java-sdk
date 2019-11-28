package io.leia.builder.params;

import io.leia.client.model.Model;
import io.leia.client.model.ModelInputTypes;
import io.leia.client.model.ModelTypes;

import java.time.OffsetDateTime;
import java.util.List;

public class GetModelsParams {
    private String modelId = null;
    private ModelTypes modelType = null;
    private String name = null;
    private String description = null;
    private List<ModelInputTypes> inputTypes = null;
    private List<String> tags = null;
    private OffsetDateTime createdAfter = null;
    private OffsetDateTime createdBefore = null;
    private Boolean onlyMime = null;
    private String sort = null;
    private Integer offset = null;
    private Integer limit = null;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public void setModel(Model model) {
        this.modelId = model.getId();
    }


    public ModelTypes getModelType() {
        return modelType;
    }

    public void setModelType(ModelTypes modelType) {
        this.modelType = modelType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ModelInputTypes> getInputTypes() {
        return inputTypes;
    }

    public void setInputTypes(List<ModelInputTypes> inputTypes) {
        this.inputTypes = inputTypes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public OffsetDateTime getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(OffsetDateTime createdAfter) {
        this.createdAfter = createdAfter;
    }

    public OffsetDateTime getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(OffsetDateTime createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Boolean getOnlyMime() {
        return onlyMime;
    }

    public void setOnlyMime(Boolean onlyMime) {
        this.onlyMime = onlyMime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
