package be.kdg.prog6.statistics.ports.in.command;

import be.kdg.prog6.statistics.domain.PredictionType;
import java.util.Map;

public record PredictionCommand(PredictionType predictionType, Map<String, Object> playerData) {}
