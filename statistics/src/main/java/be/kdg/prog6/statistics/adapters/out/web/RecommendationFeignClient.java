package be.kdg.prog6.statistics.adapters.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "recommendationService", url = "http://chatbox.westeurope.cloudapp.azure.com:8000/api/recommend")
public interface RecommendationFeignClient {
    @GetMapping("/collaborative/{userId}")
    String getRawCollaborativeRecommendations(@PathVariable String userId);

    @GetMapping("/content/{gameId}")
    String getRawContentRecommendations(@PathVariable String gameId);
}
