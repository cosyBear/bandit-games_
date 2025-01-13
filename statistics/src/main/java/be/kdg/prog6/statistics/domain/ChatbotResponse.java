package be.kdg.prog6.statistics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatbotResponse {
    private String userQuery;
    private String response;
    private String context;
    private LocalDateTime timestamp;
}
