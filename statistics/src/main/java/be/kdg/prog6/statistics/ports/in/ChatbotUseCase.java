package be.kdg.prog6.statistics.ports.in;

import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;

public interface ChatbotUseCase {
    ChatbotResponse getResponse(ChatbotCommand command);
}
