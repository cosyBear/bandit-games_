package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.web.PredictionFeignClient;
import be.kdg.prog6.statistics.ports.out.PredictionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PredictionServiceAdapter implements PredictionServicePort {
    private final PredictionFeignClient feignClient;
    @Override
    public Map<String, Object> predictChurn(Map<String, Object> playerData) {
        return feignClient.predictChurn(playerData);
    }

    @Override
    public Map<String, Object> predictEngagement(Map<String, Object> playerData) {
        return feignClient.predictEngagement(playerData);
    }

    @Override
    public Map<String, Object> predictWinProbability(Map<String, Object> playerData) {
        return feignClient.predictWinProbability(playerData);
    }

    @Override
    public Map<String, Object> classifyPlayer(Map<String, Object> playerData) {
        return feignClient.classifyPlayer(playerData);
    }
}
