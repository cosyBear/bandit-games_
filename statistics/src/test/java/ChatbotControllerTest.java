import be.kdg.prog6.statistics.adapters.in.ChatbotController;
import be.kdg.prog6.statistics.domain.ChatbotResponse;
import be.kdg.prog6.statistics.ports.in.ChatbotUseCase;
import be.kdg.prog6.statistics.ports.in.command.ChatbotCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ChatbotController.class)
@ContextConfiguration(classes = {ChatbotController.class})
class ChatbotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatbotUseCase chatbotUseCase;

    @Test
    void testGetChatbotResponse() throws Exception {
        // Arrange
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
