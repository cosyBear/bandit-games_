package be.kdg.prog6.statistics.domain;

import lombok.Getter;

@Getter
public enum RecommendationType {
    COLLABORATIVE("collaborative"),
    CONTENT_BASED("content-based");

    private final String type;

    RecommendationType(String type) {
        this.type = type;
    }

    public static RecommendationType fromType(String type) {
        for (RecommendationType recommendationType : values()) {
            if (recommendationType.type.equalsIgnoreCase(type)) {
                return recommendationType;
            }
        }
        throw new IllegalArgumentException("Invalid RecommendationType: " + type);
    }
}