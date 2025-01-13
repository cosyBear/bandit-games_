package be.kdg.prog6.statistics.adapters.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatbotRequest {
    private String userInput;
    private String context;
}
