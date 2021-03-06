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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Worker
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-11-26T15:53:43.006+01:00[Europe/Paris]")
public class Worker {
  public static final String SERIALIZED_NAME_JOB_TYPE = "job_type";
  @SerializedName(SERIALIZED_NAME_JOB_TYPE)
  private String jobType;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  @SerializedName(SERIALIZED_NAME_NUMBER)
  private Integer number;

  public static final String SERIALIZED_NAME_STATUSES = "statuses";
  @SerializedName(SERIALIZED_NAME_STATUSES)
  private List<String> statuses = new ArrayList<>();


  public Worker jobType(String jobType) {
    
    this.jobType = jobType;
    return this;
  }

   /**
   * Get jobType
   * @return jobType
  **/
  @ApiModelProperty(example = "predict-507f191e810c19729de860ec", required = true, value = "")

  public String getJobType() {
    return jobType;
  }


  public void setJobType(String jobType) {
    this.jobType = jobType;
  }


  public Worker number(Integer number) {
    
    this.number = number;
    return this;
  }

   /**
   * Get number
   * @return number
  **/
  @ApiModelProperty(example = "3", required = true, value = "")

  public Integer getNumber() {
    return number;
  }


  public void setNumber(Integer number) {
    this.number = number;
  }


  public Worker statuses(List<String> statuses) {
    
    this.statuses = statuses;
    return this;
  }

  public Worker addStatusesItem(String statusesItem) {
    this.statuses.add(statusesItem);
    return this;
  }

   /**
   * Get statuses
   * @return statuses
  **/
  @ApiModelProperty(example = "[\"RUNNING\",\"RUNNING\",\"STARTING\"]", required = true, value = "")

  public List<String> getStatuses() {
    return statuses;
  }


  public void setStatuses(List<String> statuses) {
    this.statuses = statuses;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Worker worker = (Worker) o;
    return Objects.equals(this.jobType, worker.jobType) &&
        Objects.equals(this.number, worker.number) &&
        Objects.equals(this.statuses, worker.statuses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobType, number, statuses);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Worker {\n");
    sb.append("    jobType: ").append(toIndentedString(jobType)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    statuses: ").append(toIndentedString(statuses)).append("\n");
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

