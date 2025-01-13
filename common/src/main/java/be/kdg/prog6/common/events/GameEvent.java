package be.kdg.prog6.common.events;

import java.util.List;
import java.util.UUID;

public record GameEvent(
        UUID id,
        String gameName,
        String gameType,
        List<AchievementEvent> achievementList,
        String imageUrl ,
        String backgroundImageUrl,
        String description,
        boolean favourite,
        String domainUrl
        ) {
}
