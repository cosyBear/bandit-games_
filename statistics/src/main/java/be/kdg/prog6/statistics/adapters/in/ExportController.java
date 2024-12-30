package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.in.command.ExportCommand;
import be.kdg.prog6.statistics.ports.in.ExportPlayerStatisticsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/export")
public class ExportController {
    private final ExportPlayerStatisticsUseCase exportUseCase;

    public ExportController(ExportPlayerStatisticsUseCase exportUseCase) {
        this.exportUseCase = exportUseCase;
    }

    @PostMapping("/player-statistics")
    public ResponseEntity<byte[]> exportPlayerStatistics() {
        ExportCommand command = new ExportCommand(UUID.randomUUID());

        ExportData exportData = exportUseCase.exportAllStatistics(command);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + exportData.getFileName())
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(exportData.getFileContent());
    }
}
