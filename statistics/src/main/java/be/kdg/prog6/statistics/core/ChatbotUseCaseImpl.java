package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import be.kdg.prog6.statistics.ports.in.ChatbotUseCase;
import be.kdg.prog6.statistics.ports.out.ChatbotServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatbotUseCaseImpl implements ChatbotUseCase {
    private final ChatbotServicePort chatbotServicePort;

    @Override
    public ChatbotResponse getResponse(ChatbotCommand command) {
        String userInput = command.userInput();
        String context = command.context();

        return chatbotServicePort.getChatBotResponse(userInput, context);
    }
}
