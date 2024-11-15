package be.kdg.prog6.boundedcontextA.port.in;


public record AchievementCommand(
        String achievementName,
        String achievementDescription,
        String imageUrl,
        boolean achieved
) {
}
