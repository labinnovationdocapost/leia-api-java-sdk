package io.leia.builder;

import io.leia.builder.params.AddModelParams;

import java.io.InputStream;
import java.util.List;

public final class AddModelParamsBuilder {
    private AddModelParams addModelParams;

    private AddModelParamsBuilder() {
        addModelParams = new AddModelParams();
    }

    public static AddModelParamsBuilder create(String applicationId, String name, InputStream file) {
        return new AddModelParamsBuilder().withApplicationId(applicationId).withFile(file).withName(name);
    }

    public AddModelParamsBuilder withApplicationId(String applicationId) {
        addModelParams.setApplicationId(applicationId);
        return this;
    }

    public AddModelParamsBuilder withName(String name) {
        addModelParams.setName(name);
        return this;
    }

    public AddModelParamsBuilder withFile(InputStream file) {
        addModelParams.setFile(file);
        return this;
    }

    public AddModelParamsBuilder withDescription(String description) {
        addModelParams.setDescription(description);
        return this;
    }

    public AddModelParamsBuilder withTtl(Integer ttl) {
        addModelParams.setTtl(ttl);
        return this;
    }

    public AddModelParamsBuilder withAllowedApplicationIds(List<String> allowedApplicationIds) {
        addModelParams.setAllowedApplicationIds(allowedApplicationIds);
        return this;
    }

    public AddModelParamsBuilder withAllowAllApplications(Boolean allowAllApplications) {
        addModelParams.setAllowAllApplications(allowAllApplications);
        return this;
    }

    public AddModelParamsBuilder withTags(List<String> tags) {
        addModelParams.setTags(tags);
        return this;
    }

    public AddModelParams build() {
        return addModelParams;
    }
}
