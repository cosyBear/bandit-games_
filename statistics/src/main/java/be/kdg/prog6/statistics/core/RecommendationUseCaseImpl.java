package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.in.RecommendationUseCase;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationUseCaseImpl implements RecommendationUseCase {
    private final RecommendationServicePort recommendationServicePort;

    @Override
    public Recommendation recommend(RecommendationCommand command) {
        switch (command.recommendationType()) {
            case COLLABORATIVE -> {
                return recommendationServicePort.getCollaborativeRecommendations(command.id());
            }
            case CONTENT_BASED -> {
                return recommendationServicePort.getContentBasedRecommendations(command.id());
            }
            default -> throw new IllegalArgumentException("Invalid recommendation type: " + command.recommendationType());
        }
    }
}
