package be.kdg.prog6.storeBoundedContext.port.in.command;

import java.util.List;

public record CreateGameCommand(
        String gameName,
        String gameType,
        List<AchievementCommand> achievements,
        String imageUrl,
        String backgroundImageUrl,
        String description,
        double price,
        String domainUrl
) {
}



