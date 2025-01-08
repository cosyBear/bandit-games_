package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.web.RecommendationFeignClient;
import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationServiceAdapter implements RecommendationServicePort {
    private final RecommendationFeignClient feignClient;

    @Override
    public Recommendation getCollaborativeRecommendations(String userId) {
        List<String> recommendations = feignClient.getRawCollaborativeRecommendations(userId);
        return new Recommendation(UUID.fromString(userId), recommendations);
    }

    @Override
    public Recommendation getContentBasedRecommendations(String gameId) {
        List<String> recommendations = feignClient.getRawContentRecommendations(gameId);
        return new Recommendation(null, recommendations);
    }
}
