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
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets AnnotationTypes
 */
@JsonAdapter(AnnotationTypes.Adapter.class)
public enum AnnotationTypes {
  
  BOX("BOX"),
  
  TEXT("TEXT"),
  
  TYPING("TYPING");

  private String value;

  AnnotationTypes(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static AnnotationTypes fromValue(String value) {
    for (AnnotationTypes b : AnnotationTypes.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<AnnotationTypes> {
    @Override
    public void write(final JsonWriter jsonWriter, final AnnotationTypes enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public AnnotationTypes read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return AnnotationTypes.fromValue(value);
    }
  }
}

