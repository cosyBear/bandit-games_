package be.kdg.prog6.statistics.ports.out;

import java.util.Map;

public interface PredictionServicePort {
    Map<String, Object> predictChurn(Map<String, Object> playerData);
    Map<String, Object> predictEngagement(Map<String, Object> playerData);
    Map<String, Object> predictWinProbability(Map<String, Object> playerData);
    Map<String, Object> classifyPlayer(Map<String, Object> playerData);
}
