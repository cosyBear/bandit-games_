package be.kdg.prog6.common.events;

import java.util.UUID;

public record AchievementEvent(UUID achievementId,
                               String achievementName,
                               String achievementDescription,
                               String imageUrl,
                               boolean achieved
) {
}
