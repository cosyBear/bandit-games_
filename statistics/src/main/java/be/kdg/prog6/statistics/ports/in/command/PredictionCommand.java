package be.kdg.prog6.statistics.ports.in.command;

import java.util.Map;

public record PredictionCommand(String predictionType, Map<String, Object> playerData) {}
