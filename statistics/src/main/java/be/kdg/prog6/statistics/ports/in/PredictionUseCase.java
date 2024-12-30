package be.kdg.prog6.statistics.ports.in;

import be.kdg.prog6.statistics.domain.Prediction;
import be.kdg.prog6.statistics.ports.in.command.PredictionCommand;

public interface PredictionUseCase {
    Prediction predict(PredictionCommand command);
}
