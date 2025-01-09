package be.kdg.prog6.statistics.ports.in;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;

public interface RecommendationUseCase {
    String recommend(RecommendationCommand command);
}
