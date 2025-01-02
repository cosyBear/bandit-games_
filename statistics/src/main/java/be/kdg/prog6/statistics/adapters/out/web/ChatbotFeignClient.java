package be.kdg.prog6.statistics.adapters.out.web;

import be.kdg.prog6.statistics.adapters.out.query.ChatbotQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "chatbotService", url = "http://chatbox.westeurope.cloud-app.azure.com:8000")
public interface ChatbotFeignClient {
    @PostMapping("/chatbot")
    String getRawResponse(@RequestBody ChatbotQuery payload);
}
