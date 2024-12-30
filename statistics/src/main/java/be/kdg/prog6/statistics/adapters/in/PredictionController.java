package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.Prediction;
import be.kdg.prog6.statistics.ports.in.PredictionUseCase;
import be.kdg.prog6.statistics.ports.in.command.PredictionCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/predictions")
public class PredictionController {
    private final PredictionUseCase predictionUseCase;

    public PredictionController(PredictionUseCase predictionUseCase) {
        this.predictionUseCase = predictionUseCase;
    }

    @PostMapping("/{type}")
    public ResponseEntity<Prediction> predict(
            @PathVariable("type") String predictionType,
            @RequestBody Map<String, Object> playerData
    ) {
        PredictionCommand command = new PredictionCommand(predictionType, playerData);
        Prediction prediction = predictionUseCase.predict(command);
        return ResponseEntity.ok(prediction);
    }
}
