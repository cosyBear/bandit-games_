package be.kdg.prog6.statistics.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Recommendation {
    private UUID playerId;
    private List<String> recommendedGameIds;
    private LocalDateTime generatedAt;

    public Recommendation(UUID playerId, List<String> recommendedGameIds, LocalDateTime generatedAt) {
        this.playerId = playerId;
        this.recommendedGameIds = recommendedGameIds;
        this.generatedAt = generatedAt;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public List<String> getRecommendedGameIds() {
        return recommendedGameIds;
    }

    public void setRecommendedGameIds(List<String> recommendedGameIds) {
        this.recommendedGameIds = recommendedGameIds;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
