package be.kdg.prog6.storeBoundedContext.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemRequirements {

    private String minimumOperatingSystem;
    private String minimumProcessor;
    private int minimumMemoryInGB;
    private String minimumGraphicsCard;
    private int minimumStorageInGB;
    private String minimumDirectXVersion;

    private String recommendedOperatingSystem;
    private String recommendedProcessor;
    private int recommendedMemoryInGB;
    private String recommendedGraphicsCard;
    private int recommendedStorageInGB;
    private String recommendedDirectXVersion;

    public SystemRequirements(String minimumOperatingSystem, String minimumProcessor, int minimumMemoryInGB, String minimumGraphicsCard, int minimumStorageInGB, String minimumDirectXVersion, String recommendedOperatingSystem, String recommendedProcessor, int recommendedMemoryInGB, String recommendedGraphicsCard, int recommendedStorageInGB, String recommendedDirectXVersion) {
        this.minimumOperatingSystem = minimumOperatingSystem;
        this.minimumProcessor = minimumProcessor;
        this.minimumMemoryInGB = minimumMemoryInGB;
        this.minimumGraphicsCard = minimumGraphicsCard;
        this.minimumStorageInGB = minimumStorageInGB;
        this.minimumDirectXVersion = minimumDirectXVersion;
        this.recommendedOperatingSystem = recommendedOperatingSystem;
        this.recommendedProcessor = recommendedProcessor;
        this.recommendedMemoryInGB = recommendedMemoryInGB;
        this.recommendedGraphicsCard = recommendedGraphicsCard;
        this.recommendedStorageInGB = recommendedStorageInGB;
        this.recommendedDirectXVersion = recommendedDirectXVersion;
    }
}
