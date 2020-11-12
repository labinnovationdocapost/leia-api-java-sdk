package io.leia.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public enum ConditionOperatorTypes {
    EQUAL("$eq"),
    GREATER("$gt"),
    GREATER_OR_EQUAL("$gte"),
    IN("$in"),
    LOWER("$lt"),
    LOWER_OR_EQUAL("$lte"),
    NOT_EQUAL("$ne"),
    NOT_IN("$nin");

    private String value;

    ConditionOperatorTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ConditionOperatorTypes fromValue(String value) {
        for (ConditionOperatorTypes b : ConditionOperatorTypes.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<ConditionOperatorTypes> {
        @Override
        public void write(final JsonWriter jsonWriter, final ConditionOperatorTypes enumeration) throws IOException {
            jsonWriter.value(enumeration.getValue());
        }

        @Override
        public ConditionOperatorTypes read(final JsonReader jsonReader) throws IOException {
            String value = jsonReader.nextString();
            return ConditionOperatorTypes.fromValue(value);
        }
    }
}
