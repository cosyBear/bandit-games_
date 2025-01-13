package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.query.ChatbotQuery;
import be.kdg.prog6.statistics.adapters.out.web.ChatbotFeignClient;
import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.out.ChatbotServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatbotServiceAdapter implements ChatbotServicePort {

    private final ChatbotFeignClient feignClient;

    @Override
    public ChatbotResponse getChatBotResponse(String userInput, String context) {
        ChatbotQuery payload = new ChatbotQuery(userInput, context);
        String rawResponse = feignClient.getRawResponse(payload);
        return new ChatbotResponse(userInput, rawResponse, context, LocalDateTime.now());
    }
}