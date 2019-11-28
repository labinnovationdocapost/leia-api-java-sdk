package io.leia.builder;

import io.leia.builder.params.GetModelsParams;
import io.leia.client.model.Model;
import io.leia.client.model.ModelInputTypes;
import io.leia.client.model.ModelTypes;

import java.time.OffsetDateTime;
import java.util.List;

public final class GetModelsParamsBuilder {
    private GetModelsParams getModelsParams;

    private GetModelsParamsBuilder() {
        getModelsParams = new GetModelsParams();
    }

    public static GetModelsParamsBuilder create() {
        return new GetModelsParamsBuilder();
    }

    public GetModelsParamsBuilder withModelId(String modelId) {
        getModelsParams.setModelId(modelId);
        return this;
    }

    public GetModelsParamsBuilder withModel(Model model) {
        getModelsParams.setModel(model);
        return this;
    }

    public GetModelsParamsBuilder withModelType(ModelTypes modelType) {
        getModelsParams.setModelType(modelType);
        return this;
    }

    public GetModelsParamsBuilder withName(String name) {
        getModelsParams.setName(name);
        return this;
    }

    public GetModelsParamsBuilder withDescription(String description) {
        getModelsParams.setDescription(description);
        return this;
    }

    public GetModelsParamsBuilder withInputTypes(List<ModelInputTypes> inputTypes) {
        getModelsParams.setInputTypes(inputTypes);
        return this;
    }

    public GetModelsParamsBuilder withTags(List<String> tags) {
        getModelsParams.setTags(tags);
        return this;
    }

    public GetModelsParamsBuilder withCreatedAfter(OffsetDateTime createdAfter) {
        getModelsParams.setCreatedAfter(createdAfter);
        return this;
    }

    public GetModelsParamsBuilder withCreatedBefore(OffsetDateTime createdBefore) {
        getModelsParams.setCreatedBefore(createdBefore);
        return this;
    }

    public GetModelsParamsBuilder withOnlyMime(Boolean onlyMime) {
        getModelsParams.setOnlyMime(onlyMime);
        return this;
    }

    public GetModelsParamsBuilder withSort(String sort) {
        getModelsParams.setSort(sort);
        return this;
    }

    public GetModelsParamsBuilder withOffset(Integer offset) {
        getModelsParams.setOffset(offset);
        return this;
    }

    public GetModelsParamsBuilder withLimit(Integer limit) {
        getModelsParams.setLimit(limit);
        return this;
    }

    public GetModelsParams build() {
        return getModelsParams;
    }
}
