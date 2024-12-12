import be.kdg.prog6.statistics.core.RecommendationUseCaseImpl;
import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class RecommendationUseCaseImplTest {
    private RecommendationUseCaseImpl useCase;

    @Mock
    private RecommendationServicePort recommendationServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RecommendationUseCaseImpl(recommendationServicePort);
    }

    @Test
    void testRecommendCollaboratively() {
        // Arrange
        String userId = UUID.randomUUID().toString();
        Recommendation expectedRecommendation = new Recommendation(
                UUID.fromString(userId),
                List.of("game1", "game2", "game3"),
                LocalDateTime.now()
        );

        when(recommendationServicePort.getCollaborativeRecommendations(userId)).thenReturn(expectedRecommendation);

        RecommendationCommand command = new RecommendationCommand("collaborative", userId);

        // Act
        Recommendation actualRecommendation = useCase.recommend(command);

        // Assert
        assertEquals(expectedRecommendation, actualRecommendation);
        verify(recommendationServicePort, times(1)).getCollaborativeRecommendations(userId);
    }
}
