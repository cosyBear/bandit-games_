package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.web.RecommendationFeignClient;
import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationServiceAdapter implements RecommendationServicePort {
    private final RecommendationFeignClient feignClient;

    @Override
    public String getCollaborativeRecommendations(String userId) {
        return feignClient.getRawCollaborativeRecommendations(userId);
    }

    @Override
    public String getContentBasedRecommendations(String gameId) {
        return feignClient.getRawContentRecommendations(gameId);
    }
}
