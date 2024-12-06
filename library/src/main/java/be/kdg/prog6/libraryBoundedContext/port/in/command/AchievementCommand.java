package be.kdg.prog6.libraryBoundedContext.port.in.command;


public record AchievementCommand(
        String achievementName,
        String achievementDescription,
        String imageUrl,
        boolean achieved
) {
}
