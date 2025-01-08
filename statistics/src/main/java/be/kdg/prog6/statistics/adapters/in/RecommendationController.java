package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.domain.RecommendationType;
import be.kdg.prog6.statistics.ports.in.RecommendationUseCase;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationUseCase recommendationUseCase;

    @GetMapping("/{type}/{id}")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<Recommendation> recommend(
            @PathVariable("type")RecommendationType type,
            @PathVariable("id") String id
    ) {
        RecommendationCommand command = new RecommendationCommand(type, id);
        Recommendation recommendation = recommendationUseCase.recommend(command);
        return ResponseEntity.ok(recommendation);
    }
}
