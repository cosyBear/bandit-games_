package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.query.ChatbotQuery;
import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.out.ChatbotServicePort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatbotServiceAdapter implements ChatbotServicePort {
    private final ChatbotFeignClient feignClient;

    public ChatbotServiceAdapter(ChatbotFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public ChatbotResponse getChatBotResponse(String userInput, String context) {
        ChatbotQuery payload = new ChatbotQuery(userInput, context);
        String rawResponse = feignClient.getRawResponse(payload);
        return new ChatbotResponse(userInput, rawResponse, context, LocalDateTime.now());
    }
}

@FeignClient(name = "chatbotService", url = "http://chatbox.westeurope.cloud-app.azure.com:8000")
interface ChatbotFeignClient {
    @PostMapping("/chatbot")
    String getRawResponse(@RequestBody ChatbotQuery payload);
}
