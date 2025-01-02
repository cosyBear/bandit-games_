package be.kdg.prog6.statistics.ports.out;

import be.kdg.prog6.statistics.domain.ChatbotResponse;

public interface ChatbotServicePort {
    ChatbotResponse getChatBotResponse(String userInput, String context);
}
