package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.Prediction;
import be.kdg.prog6.statistics.domain.PredictionType;
import be.kdg.prog6.statistics.ports.in.PredictionUseCase;
import be.kdg.prog6.statistics.ports.in.command.PredictionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/predictions")
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionUseCase predictionUseCase;

    @PostMapping("/{type}")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<Prediction> predict(
            @PathVariable("type") String predictionType,
            @RequestBody Map<String, Object> playerData
    ) {
        PredictionType type = PredictionType.valueOf(predictionType.toUpperCase());

        PredictionCommand command = new PredictionCommand(type, playerData);
        Prediction prediction = predictionUseCase.predict(command);
        return ResponseEntity.ok(prediction);
    }
}
