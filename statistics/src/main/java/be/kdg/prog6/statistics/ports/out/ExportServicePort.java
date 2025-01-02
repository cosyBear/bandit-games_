package be.kdg.prog6.statistics.ports.out;

import be.kdg.prog6.statistics.domain.ExportData;

public interface ExportServicePort {
    ExportData exportPlayerStatistics();
}