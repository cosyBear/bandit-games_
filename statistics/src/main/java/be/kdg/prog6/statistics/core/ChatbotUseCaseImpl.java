package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import be.kdg.prog6.statistics.ports.in.ChatbotUseCase;
import be.kdg.prog6.statistics.ports.out.ChatbotServicePort;
import org.springframework.stereotype.Service;

@Service
public class ChatbotUseCaseImpl implements ChatbotUseCase {
    private final ChatbotServicePort chatbotServicePort;

    public ChatbotUseCaseImpl(ChatbotServicePort chatbotServicePort) {
        this.chatbotServicePort = chatbotServicePort;
    }

    @Override
    public ChatbotResponse getResponse(ChatbotCommand command) {
        String userInput = command.userInput();
        String context = command.context();

        return chatbotServicePort.getChatBotResponse(userInput, context);
    }
}
