package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import be.kdg.prog6.statistics.ports.out.ChatbotServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatbotUseCaseImplTest {
    private ChatbotUseCaseImpl chatbotUseCase;

    @Mock
    private ChatbotServicePort chatbotServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chatbotUseCase = new ChatbotUseCaseImpl(chatbotServicePort);
    }

    @Test
    void testGetResponse() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String userInput = "What are the rules for chess?";
        String context = "Game rules";
        ChatbotCommand command = new ChatbotCommand(userId, userInput, context);

        ChatbotResponse expectedResponse = new ChatbotResponse(
                userInput,
                "Chess is played on an 8x8 board...",
                context,
                LocalDateTime.now()
        );

        when(chatbotServicePort.getChatBotResponse(userInput, context)).thenReturn(expectedResponse);

        // Act
        ChatbotResponse actualResponse = chatbotUseCase.getResponse(command);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(chatbotServicePort, times(1)).getChatBotResponse(userInput, context);
    }
}
