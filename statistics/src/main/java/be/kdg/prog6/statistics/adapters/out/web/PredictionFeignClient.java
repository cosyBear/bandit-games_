package be.kdg.prog6.statistics.adapters.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "predictionService", url = "http:///chatbox.westeurope.cloudapp.azure.com:8000")
public interface PredictionFeignClient {
    @PostMapping("api/predict-churn")
    Map<String, Object> predictChurn(@RequestBody Map<String, Object> playerData);

    @PostMapping("api/predict-engagement")
    Map<String, Object> predictEngagement(@RequestBody Map<String, Object> playerData);

    @PostMapping("api/predict-win-probability")
    Map<String, Object> predictWinProbability(@RequestBody Map<String, Object> playerData);

    @PostMapping("api/classify-player")
    Map<String, Object> classifyPlayer(@RequestBody Map<String, Object> playerData);
}
