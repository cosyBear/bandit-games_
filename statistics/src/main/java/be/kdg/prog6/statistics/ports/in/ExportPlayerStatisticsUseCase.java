package be.kdg.prog6.statistics.ports.in;

import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.in.command.ExportCommand;

public interface ExportPlayerStatisticsUseCase {
    ExportData exportAllStatistics(ExportCommand command);
}
