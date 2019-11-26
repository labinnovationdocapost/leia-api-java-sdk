/*
 * LEIA RESTful API for AI
 * Leia API
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: sebastien.favre@docapost.fr
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.leia.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.leia.client.model.ModelInputTypes;
import io.leia.client.model.ModelTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-11-22T11:39:43.225+01:00[Europe/Paris]")
public class Model {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_APPLICATION_ID = "application_id";
  @SerializedName(SERIALIZED_NAME_APPLICATION_ID)
  private String applicationId;

  public static final String SERIALIZED_NAME_ALLOWED_APPLICATION_IDS = "allowed_application_ids";
  @SerializedName(SERIALIZED_NAME_ALLOWED_APPLICATION_IDS)
  private List<String> allowedApplicationIds = null;

  public static final String SERIALIZED_NAME_ALLOW_ALL_APPLICATIONS = "allow_all_applications";
  @SerializedName(SERIALIZED_NAME_ALLOW_ALL_APPLICATIONS)
  private Boolean allowAllApplications;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_MODEL_TYPE = "model_type";
  @SerializedName(SERIALIZED_NAME_MODEL_TYPE)
  private ModelTypes modelType;

  public static final String SERIALIZED_NAME_INPUT_TYPES = "input_types";
  @SerializedName(SERIALIZED_NAME_INPUT_TYPES)
  private List<ModelInputTypes> inputTypes = new ArrayList<>();

  public static final String SERIALIZED_NAME_CREATION_TIME = "creation_time";
  @SerializedName(SERIALIZED_NAME_CREATION_TIME)
  private OffsetDateTime creationTime;

  public static final String SERIALIZED_NAME_TAGS = "tags";
  @SerializedName(SERIALIZED_NAME_TAGS)
  private List<String> tags = null;


  public Model id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "507f191e810c19729de860ea", required = true, value = "")

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public Model applicationId(String applicationId) {
    
    this.applicationId = applicationId;
    return this;
  }

   /**
   * Get applicationId
   * @return applicationId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "507f191e810c19729de860ea", value = "")

  public String getApplicationId() {
    return applicationId;
  }


  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }


  public Model allowedApplicationIds(List<String> allowedApplicationIds) {
    
    this.allowedApplicationIds = allowedApplicationIds;
    return this;
  }

  public Model addAllowedApplicationIdsItem(String allowedApplicationIdsItem) {
    if (this.allowedApplicationIds == null) {
      this.allowedApplicationIds = new ArrayList<>();
    }
    this.allowedApplicationIds.add(allowedApplicationIdsItem);
    return this;
  }

   /**
   * Get allowedApplicationIds
   * @return allowedApplicationIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getAllowedApplicationIds() {
    return allowedApplicationIds;
  }


  public void setAllowedApplicationIds(List<String> allowedApplicationIds) {
    this.allowedApplicationIds = allowedApplicationIds;
  }


  public Model allowAllApplications(Boolean allowAllApplications) {
    
    this.allowAllApplications = allowAllApplications;
    return this;
  }

   /**
   * Get allowAllApplications
   * @return allowAllApplications
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "")

  public Boolean isAllowAllApplications() {
    return allowAllApplications;
  }


  public void setAllowAllApplications(Boolean allowAllApplications) {
    this.allowAllApplications = allowAllApplications;
  }


  public Model name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "my_classification_model", required = true, value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public Model description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "This is a description of my model", value = "")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public Model modelType(ModelTypes modelType) {
    
    this.modelType = modelType;
    return this;
  }

   /**
   * Get modelType
   * @return modelType
  **/
  @ApiModelProperty(required = true, value = "")

  public ModelTypes getModelType() {
    return modelType;
  }


  public void setModelType(ModelTypes modelType) {
    this.modelType = modelType;
  }


  public Model inputTypes(List<ModelInputTypes> inputTypes) {
    
    this.inputTypes = inputTypes;
    return this;
  }

  public Model addInputTypesItem(ModelInputTypes inputTypesItem) {
    this.inputTypes.add(inputTypesItem);
    return this;
  }

   /**
   * Get inputTypes
   * @return inputTypes
  **/
  @ApiModelProperty(required = true, value = "")

  public List<ModelInputTypes> getInputTypes() {
    return inputTypes;
  }


  public void setInputTypes(List<ModelInputTypes> inputTypes) {
    this.inputTypes = inputTypes;
  }


  public Model creationTime(OffsetDateTime creationTime) {
    
    this.creationTime = creationTime;
    return this;
  }

   /**
   * Get creationTime
   * @return creationTime
  **/
  @ApiModelProperty(example = "2018-11-07T16:02:29.761Z", required = true, value = "")

  public OffsetDateTime getCreationTime() {
    return creationTime;
  }


  public void setCreationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
  }


  public Model tags(List<String> tags) {
    
    this.tags = tags;
    return this;
  }

  public Model addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getTags() {
    return tags;
  }


  public void setTags(List<String> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Model model = (Model) o;
    return Objects.equals(this.id, model.id) &&
        Objects.equals(this.applicationId, model.applicationId) &&
        Objects.equals(this.allowedApplicationIds, model.allowedApplicationIds) &&
        Objects.equals(this.allowAllApplications, model.allowAllApplications) &&
        Objects.equals(this.name, model.name) &&
        Objects.equals(this.description, model.description) &&
        Objects.equals(this.modelType, model.modelType) &&
        Objects.equals(this.inputTypes, model.inputTypes) &&
        Objects.equals(this.creationTime, model.creationTime) &&
        Objects.equals(this.tags, model.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, applicationId, allowedApplicationIds, allowAllApplications, name, description, modelType, inputTypes, creationTime, tags);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
    sb.append("    allowedApplicationIds: ").append(toIndentedString(allowedApplicationIds)).append("\n");
    sb.append("    allowAllApplications: ").append(toIndentedString(allowAllApplications)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    modelType: ").append(toIndentedString(modelType)).append("\n");
    sb.append("    inputTypes: ").append(toIndentedString(inputTypes)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

