package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.Prediction;
import be.kdg.prog6.statistics.ports.in.PredictionUseCase;
import be.kdg.prog6.statistics.ports.in.command.PredictionCommand;
import be.kdg.prog6.statistics.ports.out.PredictionServicePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class PredictionUseCaseImpl implements PredictionUseCase {
    private final PredictionServicePort predictionServicePort;

    public PredictionUseCaseImpl(PredictionServicePort predictionServicePort) {
        this.predictionServicePort = predictionServicePort;
    }

    @Override
    public Prediction predict(PredictionCommand command) {
        Map<String, Object> result;

        switch (command.predictionType().toLowerCase()) {
            case "churn" -> result = predictionServicePort.predictChurn(command.playerData());
            case "engagement" -> result = predictionServicePort.predictEngagement(command.playerData());
            case "win-probability" -> result = predictionServicePort.predictWinProbability(command.playerData());
            case "classification" -> result = predictionServicePort.classifyPlayer(command.playerData());
            default -> throw new IllegalArgumentException("Unsupported prediction type: " + command.predictionType());
        }

        return new Prediction(
                UUID.fromString(result.get("playerId").toString()),
                command.predictionType(),
                Double.parseDouble(result.get("value").toString()),
                LocalDateTime.now()
        );
    }

}
