package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.ports.out.PredictionServicePort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PredictionServiceAdapter implements PredictionServicePort {
    private final PredictionFeignClient feignClient;

    public PredictionServiceAdapter(PredictionFeignClient feignClient) {
        this.feignClient = feignClient;
    }

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

@FeignClient(name = "predictionService", url = "http:///chatbox.westeurope.cloudapp.azure.com:8000")
interface PredictionFeignClient {
    @PostMapping("api/predict-churn")
    Map<String, Object> predictChurn(@RequestBody Map<String, Object> playerData);

    @PostMapping("api/predict-engagement")
    Map<String, Object> predictEngagement(@RequestBody Map<String, Object> playerData);

    @PostMapping("api/predict-win-probability")
    Map<String, Object> predictWinProbability(@RequestBody Map<String, Object> playerData);

    @PostMapping("api/classify-player")
    Map<String, Object> classifyPlayer(@RequestBody Map<String, Object> playerData);
}
