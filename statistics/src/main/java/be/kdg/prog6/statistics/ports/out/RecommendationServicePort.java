package be.kdg.prog6.statistics.ports.out;

import be.kdg.prog6.statistics.domain.Recommendation;

public interface RecommendationServicePort {
    Recommendation getCollaborativeRecommendations(String userId);
    Recommendation getContentBasedRecommendations(String gameId);
}
