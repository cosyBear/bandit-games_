package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.domain.RecommendationType;
import be.kdg.prog6.statistics.ports.in.RecommendationUseCase;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationUseCase recommendationUseCase;

    @GetMapping("/{type}")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<String> recommend(
            @PathVariable("type")String type,
            @AuthenticationPrincipal Jwt token
    ) {
        String userId = token.getClaimAsString("UserId");

        RecommendationType recommendationType = RecommendationType.fromType(type);
        RecommendationCommand command = new RecommendationCommand(recommendationType, userId);

        String recommendation = recommendationUseCase.recommend(command);
        return ResponseEntity.ok(recommendation);
    }
}
