import be.kdg.prog6.statistics.StatisticsBoundedContextApplication;
import be.kdg.prog6.statistics.adapters.in.ChatbotController;
import be.kdg.prog6.statistics.core.ExportPlayerStatisticsUseCaseImpl;
import be.kdg.prog6.statistics.domain.ExportData;

import be.kdg.prog6.statistics.ports.in.command.ExportCommand;
import be.kdg.prog6.statistics.ports.out.ExportServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ExportPlayerStatisticsUseCaseImpl.class})
class ExportPlayerStatisticsUseCaseImplTest {
    private ExportPlayerStatisticsUseCaseImpl exportUseCase;

    @Mock
    private ExportServicePort exportServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exportUseCase = new ExportPlayerStatisticsUseCaseImpl(exportServicePort);
    }

    @Test
    void testExportAllStatistics() {
        // Arrange
        UUID userId = UUID.randomUUID();
        ExportCommand command = new ExportCommand(userId);
        ExportData expectedData = new ExportData(
                "content".getBytes(),
                "player_statistics.csv",
                "CSV",
                LocalDateTime.now()
        );

        when(exportServicePort.exportPlayerStatistics()).thenReturn(expectedData);

        // Act
        ExportData actualData = exportUseCase.exportAllStatistics(command);

        // Assert
        assertEquals(expectedData, actualData);
        verify(exportServicePort, times(1)).exportPlayerStatistics();
    }


}
