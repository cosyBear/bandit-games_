package be.kdg.prog6.storeBoundedContext.adapters.out.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SystemRequirementsEntity {

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
}
