import be.kdg.prog6.statistics.StatisticsBoundedContextApplication;
import be.kdg.prog6.statistics.core.ExportPlayerStatisticsUseCaseImpl;
import be.kdg.prog6.statistics.core.PredictionUseCaseImpl;
import be.kdg.prog6.statistics.domain.Prediction;
import be.kdg.prog6.statistics.ports.in.command.PredictionCommand;
import be.kdg.prog6.statistics.ports.out.PredictionServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PredictionUseCaseImpl.class})
class PredictionUseCaseImplTest {
    private PredictionUseCaseImpl predictionUseCase;

    @Mock
    private PredictionServicePort predictionServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        predictionUseCase = new PredictionUseCaseImpl(predictionServicePort);
    }

    @Test
    void testPredictChurn() {
        // Arrange
        UUID playerId = UUID.randomUUID();
        String predictionType = "churn";
        Map<String, Object> playerData = Map.of(
                "Games Played", 100,
                "Wins", 50,
                "Losses", 30,
                "Draws", 20,
                "Age", 25,
                "Engagement Score", 3.5
        );

        Map<String, Object> predictionResult = Map.of(
                "playerId", playerId.toString(),
                "value", 0.85
        );

        when(predictionServicePort.predictChurn(playerData)).thenReturn(predictionResult);

        PredictionCommand command = new PredictionCommand(predictionType, playerData);

        // Act
        Prediction prediction = predictionUseCase.predict(command);

        // Assert
        assertEquals(playerId, prediction.getPlayerId());
        assertEquals(predictionType, prediction.getPredictionType());
        assertEquals(0.85, prediction.getValue());
        assertEquals(LocalDateTime.now().getYear(), prediction.getPredictedAt().getYear());
        verify(predictionServicePort, times(1)).predictChurn(playerData);
    }
}
