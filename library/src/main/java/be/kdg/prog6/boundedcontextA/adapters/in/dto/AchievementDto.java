package be.kdg.prog6.boundedcontextA.adapters.in.dto;

public record AchievementDto(
        String achievementName,
        String achievementDescription,
        String imageUrl,
        boolean achieved
) {
}