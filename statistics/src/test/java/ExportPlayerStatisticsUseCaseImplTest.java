import be.kdg.prog6.statistics.core.ExportPlayerStatisticsUseCaseImpl;
import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.in.command.ExportCommand;
import be.kdg.prog6.statistics.ports.out.ExportServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
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
                "player_statistics_" + userId + ".csv",
                "CSV",
                LocalDateTime.now()
        );

        when(exportServicePort.exportPlayerStatistics(userId)).thenReturn(expectedData);

        // Act
        ExportData actualData = exportUseCase.exportAllStatistics(command);

        // Assert
        assertEquals(expectedData, actualData);
        verify(exportServicePort, times(1)).exportPlayerStatistics(userId);
    }
}
