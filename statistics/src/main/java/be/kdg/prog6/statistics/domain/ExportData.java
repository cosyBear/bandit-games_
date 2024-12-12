package be.kdg.prog6.statistics.domain;

import java.time.LocalDateTime;

public class ExportData {
    private byte[] fileContent;
    private String fileName;
    private String fileType;
    private LocalDateTime exportedAt;

    public ExportData(byte[] fileContent, String fileName, String fileType, LocalDateTime exportedAt) {
        this.fileContent = fileContent;
        this.fileName = fileName;
        this.fileType = fileType;
        this.exportedAt = exportedAt;
    }

    // Getters and setters
    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getExportedAt() {
        return exportedAt;
    }

    public void setExportedAt(LocalDateTime exportedAt) {
        this.exportedAt = exportedAt;
    }

    public boolean isValidExport() {
        return fileContent != null && fileContent.length > 0 && fileName != null && !fileName.isEmpty();
    }
}
