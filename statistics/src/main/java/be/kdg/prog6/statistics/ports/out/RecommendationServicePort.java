package be.kdg.prog6.statistics.ports.out;

import be.kdg.prog6.statistics.domain.Recommendation;

public interface RecommendationServicePort {
    String getCollaborativeRecommendations(String userId);
    String getContentBasedRecommendations(String gameId);
}
