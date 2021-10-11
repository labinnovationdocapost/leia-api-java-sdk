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
 * ConditionalBody
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-11T17:16:03.438356+02:00[Europe/Paris]")
public class ConditionalBody {
  public static final String SERIALIZED_NAME_BLOCK_PROCESSING = "block_processing";
  @SerializedName(SERIALIZED_NAME_BLOCK_PROCESSING)
  private Boolean blockProcessing;

  public static final String SERIALIZED_NAME_CALLBACK_HEADERS = "callback_headers";
  @SerializedName(SERIALIZED_NAME_CALLBACK_HEADERS)
  private Object callbackHeaders;

  public static final String SERIALIZED_NAME_CALLBACK_URL = "callback_url";
  @SerializedName(SERIALIZED_NAME_CALLBACK_URL)
  private String callbackUrl;

  public static final String SERIALIZED_NAME_RULES = "rules";
  @SerializedName(SERIALIZED_NAME_RULES)
  private Object rules;


  public ConditionalBody blockProcessing(Boolean blockProcessing) {
    
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


  public ConditionalBody callbackHeaders(Object callbackHeaders) {
    
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


  public ConditionalBody callbackUrl(String callbackUrl) {
    
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


  public ConditionalBody rules(Object rules) {
    
    this.rules = rules;
    return this;
  }

   /**
   * Get rules
   * @return rules
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "{\"rule_name_1\":{\"conditions\":{\"field_1\":\"value_1\",\"field_2\":\"value_2\"},\"jobs\":[{\"document_ids\":[\"example_id_1\"],\"format_type\":\"classification\",\"job_type\":\"apply_model\",\"model_id\":\"example_id_1\",\"model_params\":{\"model_param_1\":\"model_param_value_1\",\"model_param_2\":\"model_param_value_2\"},\"page_range\":\"3:5\",\"tag\":\"tag\"}]},\"rule_name_2\":{\"conditions\":{\"field_1\":\"value_3\",\"field_3\":\"value_4\"},\"jobs\":[{\"document_ids\":[\"example_id_1\"],\"job_type\":\"transform\",\"output_type\":\"text\",\"page_range\":\"4:6\",\"tag\":\"tag\",\"transform_params\":{\"model_param_3\":\"model_param_value_3\",\"model_param_4\":\"model_param_value_4\"}},{\"document_ids\":[\"example_id_1\"],\"format_type\":\"classification\",\"job_type\":\"apply_model\",\"model_id\":\"example_id_2\",\"model_params\":{\"model_param_3\":\"model_param_value_3\",\"model_param_4\":\"model_param_value_4\"},\"page_range\":\"4:6\",\"tag\":\"tag\"}]}}", value = "")

  public Object getRules() {
    return rules;
  }


  public void setRules(Object rules) {
    this.rules = rules;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConditionalBody conditionalBody = (ConditionalBody) o;
    return Objects.equals(this.blockProcessing, conditionalBody.blockProcessing) &&
        Objects.equals(this.callbackHeaders, conditionalBody.callbackHeaders) &&
        Objects.equals(this.callbackUrl, conditionalBody.callbackUrl) &&
        Objects.equals(this.rules, conditionalBody.rules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockProcessing, callbackHeaders, callbackUrl, rules);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConditionalBody {\n");
    sb.append("    blockProcessing: ").append(toIndentedString(blockProcessing)).append("\n");
    sb.append("    callbackHeaders: ").append(toIndentedString(callbackHeaders)).append("\n");
    sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n");
    sb.append("    rules: ").append(toIndentedString(rules)).append("\n");
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

