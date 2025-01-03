package be.kdg.prog6.statistics.ports.out;

import be.kdg.prog6.statistics.domain.ExportData;

import java.util.UUID;

public interface ExportServicePort {
    ExportData exportPlayerStatistics(UUID userId);
}