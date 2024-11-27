package be.kdg.prog6.libraryBoundedContext.port.in;

import java.util.List;

public record CreateGameCommand(
        String gameName,
        String gameType,
        List<AchievementCommand> achievementCommandList,
        boolean favourite,
        String imageUrl
) {
}
