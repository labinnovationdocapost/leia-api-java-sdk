package io.leia.custom.client.model;

import com.google.gson.annotations.SerializedName;
import org.threeten.bp.OffsetDateTime;

public class Classification {
    public static final String SERIALIZED_ACCURACY = "accuracy";
    @SerializedName(SERIALIZED_ACCURACY)
    private Double accuracy;

    public static final String SERIALIZED_CATEGORY = "category";
    @SerializedName(SERIALIZED_CATEGORY)
    private String category;

    public static final String SERIALIZED_SCORE = "score";
    @SerializedName(SERIALIZED_SCORE)
    private Double score;

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
