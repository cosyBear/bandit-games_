
import be.kdg.prog6.statistics.adapters.in.ChatbotController;
import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import be.kdg.prog6.statistics.ports.in.ChatbotUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ChatbotControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ChatbotUseCase chatbotUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ChatbotController chatbotController = new ChatbotController(chatbotUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(chatbotController).build();
    }

    @Test
    void testGetChatbotResponse() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        String userInput = "What are the rules for chess?";
        String context = "Game rules";

        ChatbotResponse expectedResponse = new ChatbotResponse(
                userInput,
                "Chess is played on an 8x8 board...",
                context,
                LocalDateTime.now()
        );

        when(chatbotUseCase.getResponse(any(ChatbotCommand.class))).thenReturn(expectedResponse);

        String requestBody = """
                {
                    "userInput": "What are the rules for chess?",
                    "context": "Game rules"
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/chatbot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userQuery").value("What are the rules for chess?"))
                .andExpect(jsonPath("$.response").value("Chess is played on an 8x8 board..."))
                .andExpect(jsonPath("$.context").value("Game rules"));
    }
}
