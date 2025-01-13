package be.kdg.prog6.storeBoundedContext.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record SystemRequirementsCommand(
        String minimumOperatingSystem,
        String minimumProcessor,
        int minimumMemoryInGB,
        String minimumGraphicsCard,
        int minimumStorageInGB,
        String minimumDirectXVersion,
        String recommendedOperatingSystem,
        String recommendedProcessor,
        int recommendedMemoryInGB,
        String recommendedGraphicsCard,
        int recommendedStorageInGB,
        String recommendedDirectXVersion
) {}
