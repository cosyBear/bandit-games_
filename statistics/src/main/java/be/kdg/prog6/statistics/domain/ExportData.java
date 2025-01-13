package be.kdg.prog6.statistics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportData {
    private byte[] fileContent;
    private String fileName;
    private String fileType;
    private LocalDateTime exportedAt;

    public boolean isValidExport() {
        return fileContent != null && fileContent.length > 0 && fileName != null && !fileName.isEmpty();
    }
}
