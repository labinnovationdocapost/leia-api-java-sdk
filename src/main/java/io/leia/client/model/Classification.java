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
 * Classification
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-11T17:16:03.438356+02:00[Europe/Paris]")
public class Classification {
  public static final String SERIALIZED_NAME_ACCURACY = "accuracy";
  @SerializedName(SERIALIZED_NAME_ACCURACY)
  private Double accuracy;

  public static final String SERIALIZED_NAME_CATEGORY = "category";
  @SerializedName(SERIALIZED_NAME_CATEGORY)
  private String category;

  public static final String SERIALIZED_NAME_SCORE = "score";
  @SerializedName(SERIALIZED_NAME_SCORE)
  private Double score;


  public Classification accuracy(Double accuracy) {
    
    this.accuracy = accuracy;
    return this;
  }

   /**
   * Get accuracy
   * minimum: 0
   * maximum: 1
   * @return accuracy
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "0.999", required = true, value = "")

  public Double getAccuracy() {
    return accuracy;
  }


  public void setAccuracy(Double accuracy) {
    this.accuracy = accuracy;
  }


  public Classification category(String category) {
    
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "my_class_1", required = true, value = "")

  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
    this.category = category;
  }


  public Classification score(Double score) {
    
    this.score = score;
    return this;
  }

   /**
   * Get score
   * minimum: 0
   * maximum: 1
   * @return score
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "0.999", required = true, value = "")

  public Double getScore() {
    return score;
  }


  public void setScore(Double score) {
    this.score = score;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Classification classification = (Classification) o;
    return Objects.equals(this.accuracy, classification.accuracy) &&
        Objects.equals(this.category, classification.category) &&
        Objects.equals(this.score, classification.score);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accuracy, category, score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Classification {\n");
    sb.append("    accuracy: ").append(toIndentedString(accuracy)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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

