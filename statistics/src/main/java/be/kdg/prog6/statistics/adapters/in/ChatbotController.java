package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import be.kdg.prog6.statistics.ports.in.ChatbotUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/chatbot")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ChatbotController {
    private final ChatbotUseCase chatbotUseCase;

    @PostMapping
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ChatbotResponse getChatbotResponse(@RequestBody ChatbotRequest request) {
        ChatbotCommand command = new ChatbotCommand(
                UUID.randomUUID(),
                request.getUserInput(),
                request.getContext()
        );

        return chatbotUseCase.getResponse(command);
    }
}
