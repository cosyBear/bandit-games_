package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RecommendationServiceAdapter implements RecommendationServicePort {
    private final RecommendationFeignClient feignClient;

    public RecommendationServiceAdapter(RecommendationFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public Recommendation getCollaborativeRecommendations(String userId) {
        List<String> recommendations = feignClient.getRawCollaborativeRecommendations(userId);
        return new Recommendation(UUID.fromString(userId), recommendations, LocalDateTime.now());
    }

    @Override
    public Recommendation getContentBasedRecommendations(String gameId) {
        List<String> recommendations = feignClient.getRawContentRecommendations(gameId);
        return new Recommendation(null, recommendations, LocalDateTime.now());
    }
}

@FeignClient(name = "recommendationService", url = "http:///chatbox.westeurope.cloudapp.azure.com:8000")
interface RecommendationFeignClient {
    @GetMapping("api/recommend/collaborative/{userId}")
    List<String> getRawCollaborativeRecommendations(@PathVariable String userId);

    @GetMapping("api/recommend/content/{gameId}")
    List<String> getRawContentRecommendations(@PathVariable String gameId);
}
