import be.kdg.prog6.statistics.StatisticsBoundedContextApplication;
import be.kdg.prog6.statistics.adapters.in.ChatbotController;
import be.kdg.prog6.statistics.core.ChatbotUseCaseImpl;
import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import be.kdg.prog6.statistics.ports.out.ChatbotServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ChatbotUseCaseImpl.class})
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

    @Test
    void testGetResponseWithEmptyInput() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String userInput = "";
        String context = "Game rules";
        ChatbotCommand command = new ChatbotCommand(userId, userInput, context);

        when(chatbotServicePort.getChatBotResponse(userInput, context)).thenReturn(null);

        // Act
        ChatbotResponse actualResponse = chatbotUseCase.getResponse(command);

        // Assert
        assertEquals(null, actualResponse);
        verify(chatbotServicePort, times(1)).getChatBotResponse(userInput, context);
    }

    @Test
    void testGetResponseWithException() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String userInput = "What are the rules for chess?";
        String context = "Game rules";
        ChatbotCommand command = new ChatbotCommand(userId, userInput, context);

        when(chatbotServicePort.getChatBotResponse(userInput, context))
                .thenThrow(new RuntimeException("Service unavailable"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            chatbotUseCase.getResponse(command);
        });

        assertEquals("Service unavailable", exception.getMessage());
        verify(chatbotServicePort, times(1)).getChatBotResponse(userInput, context);
    }

}
