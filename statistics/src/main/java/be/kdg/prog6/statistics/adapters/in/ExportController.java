package be.kdg.prog6.statistics.adapters.in;

import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.in.command.ExportCommand;
import be.kdg.prog6.statistics.ports.in.ExportPlayerStatisticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {
    private final ExportPlayerStatisticsUseCase exportUseCase;

    @PostMapping("/player-statistics")
    @PreAuthorize("hasAuthority('dev')")
    public ResponseEntity<byte[]> exportPlayerStatistics(@RequestParam UUID userId) {
        ExportCommand command = new ExportCommand(userId);

        ExportData exportData = exportUseCase.exportAllStatistics(command);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + exportData.getFileName())
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(exportData.getFileContent());
    }
}
