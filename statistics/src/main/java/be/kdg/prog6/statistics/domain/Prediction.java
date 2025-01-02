package be.kdg.prog6.statistics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prediction {
    //should it be a record?
    private UUID playerId;
    private String predictionType;
    private double value;
    private LocalDateTime predictedAt;
}
