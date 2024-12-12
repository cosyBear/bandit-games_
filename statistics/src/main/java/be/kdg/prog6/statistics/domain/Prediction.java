package be.kdg.prog6.statistics.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Prediction {
    //should it be a record?
    private UUID playerId;
    private String predictionType;
    private double value;
    private LocalDateTime predictedAt;

    public Prediction(UUID playerId, String predictionType, double value, LocalDateTime predictedAt) {
        this.playerId = playerId;
        this.predictionType = predictionType;
        this.value = value;
        this.predictedAt = predictedAt;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getPredictionType() {
        return predictionType;
    }

    public void setPredictionType(String predictionType) {
        this.predictionType = predictionType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }
}
