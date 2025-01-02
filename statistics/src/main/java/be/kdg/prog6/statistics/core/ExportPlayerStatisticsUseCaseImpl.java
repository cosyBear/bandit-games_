package be.kdg.prog6.statistics.core;

import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.in.command.ExportCommand;
import be.kdg.prog6.statistics.ports.in.ExportPlayerStatisticsUseCase;
import be.kdg.prog6.statistics.ports.out.ExportServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportPlayerStatisticsUseCaseImpl implements ExportPlayerStatisticsUseCase {
    private final ExportServicePort exportServicePort;

    @Override
    public ExportData exportAllStatistics(ExportCommand command) {
        return exportServicePort.exportPlayerStatistics();
    }
}
