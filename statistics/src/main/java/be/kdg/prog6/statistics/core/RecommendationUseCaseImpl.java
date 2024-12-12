package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.in.RecommendationUseCase;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import org.springframework.stereotype.Service;

@Service
public class RecommendationUseCaseImpl implements RecommendationUseCase {
    private final RecommendationServicePort recommendationServicePort;

    public RecommendationUseCaseImpl(RecommendationServicePort recommendationServicePort) {
        this.recommendationServicePort = recommendationServicePort;
    }

    @Override
    public Recommendation recommend(RecommendationCommand command) {
        switch (command.recommendationType().toLowerCase()) {
            case "collaborative" -> {
                return recommendationServicePort.getCollaborativeRecommendations(command.id());
            }
            case "content-based" -> {
                return recommendationServicePort.getContentBasedRecommendations(command.id());
            }
            default -> throw new IllegalArgumentException("Invalid recommendation type: " + command.recommendationType());
        }
    }
}
