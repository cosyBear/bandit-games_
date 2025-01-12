import be.kdg.prog6.statistics.StatisticsBoundedContextApplication;
import be.kdg.prog6.statistics.core.PredictionUseCaseImpl;
import be.kdg.prog6.statistics.core.RecommendationUseCaseImpl;
import be.kdg.prog6.statistics.domain.Recommendation;
import be.kdg.prog6.statistics.domain.RecommendationType;
import be.kdg.prog6.statistics.ports.in.command.RecommendationCommand;
import be.kdg.prog6.statistics.ports.out.RecommendationServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {RecommendationUseCaseImplTest.class})
class RecommendationUseCaseImplTest {
    private RecommendationUseCaseImpl useCase;

    @Mock
    private RecommendationServicePort recommendationServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RecommendationUseCaseImpl(recommendationServicePort);
    }

//    @Test
//    void testRecommendCollaboratively() {
//        // Arrange
//        String userId = UUID.randomUUID().toString();
//        Recommendation expectedRecommendation = new Recommendation(
//                UUID.fromString(userId),
//                List.of("game1", "game2", "game3")
//        );
//
//        when(recommendationServicePort.getCollaborativeRecommendations(userId))
//                .thenReturn(expectedRecommendation);
//
//        RecommendationCommand command = new RecommendationCommand(RecommendationType.COLLABORATIVE, userId);
//
//        // Act
//        Recommendation actualRecommendation = useCase.recommend(command);
//
//        // Assert
//        assertEquals(expectedRecommendation, actualRecommendation);
//        verify(recommendationServicePort, times(1)).getCollaborativeRecommendations(userId);
//    }
}