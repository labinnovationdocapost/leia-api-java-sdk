/*
 * LEIA RESTful API for AI
 * Leia API
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: contact@leia.io
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

/**
 * TransformBody
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-11T17:16:03.438356+02:00[Europe/Paris]")
public class TransformBody {
  public static final String SERIALIZED_NAME_BLOCK_PROCESSING = "block_processing";
  @SerializedName(SERIALIZED_NAME_BLOCK_PROCESSING)
  private Boolean blockProcessing;

  public static final String SERIALIZED_NAME_CALLBACK_HEADERS = "callback_headers";
  @SerializedName(SERIALIZED_NAME_CALLBACK_HEADERS)
  private Object callbackHeaders;

  public static final String SERIALIZED_NAME_CALLBACK_URL = "callback_url";
  @SerializedName(SERIALIZED_NAME_CALLBACK_URL)
  private String callbackUrl;

  public static final String SERIALIZED_NAME_EXECUTE_AFTER_ID = "execute_after_id";
  @SerializedName(SERIALIZED_NAME_EXECUTE_AFTER_ID)
  private String executeAfterId;

  public static final String SERIALIZED_NAME_INPUT_TAG = "input_tag";
  @SerializedName(SERIALIZED_NAME_INPUT_TAG)
  private String inputTag;

  public static final String SERIALIZED_NAME_OUTPUT_TAG = "output_tag";
  @SerializedName(SERIALIZED_NAME_OUTPUT_TAG)
  private String outputTag;

  public static final String SERIALIZED_NAME_PAGE_RANGE = "page_range";
  @SerializedName(SERIALIZED_NAME_PAGE_RANGE)
  private String pageRange;

  public static final String SERIALIZED_NAME_TRANSFORM_PARAMS = "transform_params";
  @SerializedName(SERIALIZED_NAME_TRANSFORM_PARAMS)
  private Object transformParams;


  public TransformBody blockProcessing(Boolean blockProcessing) {
    
    this.blockProcessing = blockProcessing;
    return this;
  }

   /**
   * Get blockProcessing
   * @return blockProcessing
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "")

  public Boolean getBlockProcessing() {
    return blockProcessing;
  }


  public void setBlockProcessing(Boolean blockProcessing) {
    this.blockProcessing = blockProcessing;
  }


  public TransformBody callbackHeaders(Object callbackHeaders) {
    
    this.callbackHeaders = callbackHeaders;
    return this;
  }

   /**
   * Get callbackHeaders
   * @return callbackHeaders
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "{\"header_1\":\"header_value_1\",\"header_2\":\"header_value_2\"}", value = "")

  public Object getCallbackHeaders() {
    return callbackHeaders;
  }


  public void setCallbackHeaders(Object callbackHeaders) {
    this.callbackHeaders = callbackHeaders;
  }


  public TransformBody callbackUrl(String callbackUrl) {
    
    this.callbackUrl = callbackUrl;
    return this;
  }

   /**
   * Get callbackUrl
   * @return callbackUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "http://www.example.com/callback_url", value = "")

  public String getCallbackUrl() {
    return callbackUrl;
  }


  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }


  public TransformBody executeAfterId(String executeAfterId) {
    
    this.executeAfterId = executeAfterId;
    return this;
  }

   /**
   * Get executeAfterId
   * @return executeAfterId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "example_id", value = "")

  public String getExecuteAfterId() {
    return executeAfterId;
  }


  public void setExecuteAfterId(String executeAfterId) {
    this.executeAfterId = executeAfterId;
  }


  public TransformBody inputTag(String inputTag) {
    
    this.inputTag = inputTag;
    return this;
  }

   /**
   * Get inputTag
   * @return inputTag
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "tag", value = "")

  public String getInputTag() {
    return inputTag;
  }


  public void setInputTag(String inputTag) {
    this.inputTag = inputTag;
  }


  public TransformBody outputTag(String outputTag) {
    
    this.outputTag = outputTag;
    return this;
  }

   /**
   * Get outputTag
   * @return outputTag
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "tag2", value = "")

  public String getOutputTag() {
    return outputTag;
  }


  public void setOutputTag(String outputTag) {
    this.outputTag = outputTag;
  }


  public TransformBody pageRange(String pageRange) {
    
    this.pageRange = pageRange;
    return this;
  }

   /**
   * Get pageRange
   * @return pageRange
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "3:5", value = "")

  public String getPageRange() {
    return pageRange;
  }


  public void setPageRange(String pageRange) {
    this.pageRange = pageRange;
  }


  public TransformBody transformParams(Object transformParams) {
    
    this.transformParams = transformParams;
    return this;
  }

   /**
   * Get transformParams
   * @return transformParams
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "{\"transform_param_1\":\"transform_param_value_1\",\"transform_param_2\":\"transform_param_value_2\"}", value = "")

  public Object getTransformParams() {
    return transformParams;
  }


  public void setTransformParams(Object transformParams) {
    this.transformParams = transformParams;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransformBody transformBody = (TransformBody) o;
    return Objects.equals(this.blockProcessing, transformBody.blockProcessing) &&
        Objects.equals(this.callbackHeaders, transformBody.callbackHeaders) &&
        Objects.equals(this.callbackUrl, transformBody.callbackUrl) &&
        Objects.equals(this.executeAfterId, transformBody.executeAfterId) &&
        Objects.equals(this.inputTag, transformBody.inputTag) &&
        Objects.equals(this.outputTag, transformBody.outputTag) &&
        Objects.equals(this.pageRange, transformBody.pageRange) &&
        Objects.equals(this.transformParams, transformBody.transformParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockProcessing, callbackHeaders, callbackUrl, executeAfterId, inputTag, outputTag, pageRange, transformParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransformBody {\n");
    sb.append("    blockProcessing: ").append(toIndentedString(blockProcessing)).append("\n");
    sb.append("    callbackHeaders: ").append(toIndentedString(callbackHeaders)).append("\n");
    sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n");
    sb.append("    executeAfterId: ").append(toIndentedString(executeAfterId)).append("\n");
    sb.append("    inputTag: ").append(toIndentedString(inputTag)).append("\n");
    sb.append("    outputTag: ").append(toIndentedString(outputTag)).append("\n");
    sb.append("    pageRange: ").append(toIndentedString(pageRange)).append("\n");
    sb.append("    transformParams: ").append(toIndentedString(transformParams)).append("\n");
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

