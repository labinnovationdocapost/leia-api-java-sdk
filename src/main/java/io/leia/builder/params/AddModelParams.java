package io.leia.builder.params;

import java.io.InputStream;
import java.util.List;

public class AddModelParams {
    private String applicationId;
    private String name;
    private byte[] file;
    private String description;
    private Integer ttl;
    private List<String> allowedApplicationIds;
    private Boolean allowAllApplications;
    private List<String> tags;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public List<String> getAllowedApplicationIds() {
        return allowedApplicationIds;
    }

    public void setAllowedApplicationIds(List<String> allowedApplicationIds) {
        this.allowedApplicationIds = allowedApplicationIds;
    }

    public Boolean getAllowAllApplications() {
        return allowAllApplications;
    }

    public void setAllowAllApplications(Boolean allowAllApplications) {
        this.allowAllApplications = allowAllApplications;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
