package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.in.RecommendationUseCase;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationUseCase recommendationUseCase;

    @GetMapping("/{type}/{id}")
    public ResponseEntity<Recommendation> recommend(
            @PathVariable("type") String recommendationType,
            @PathVariable("id") String id
    ) {
        RecommendationCommand command = new RecommendationCommand(recommendationType, id);
        Recommendation recommendation = recommendationUseCase.recommend(command);
        return ResponseEntity.ok(recommendation);
    }
}
