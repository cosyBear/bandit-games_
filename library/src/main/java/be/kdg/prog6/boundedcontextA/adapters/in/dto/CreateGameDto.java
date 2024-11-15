package be.kdg.prog6.boundedcontextA.adapters.in.dto;


import java.util.List;
import java.util.Objects;

public record CreateGameDto(
        String gameName,
        String gameType,
        List<AchievementDto> achievements,
        boolean favourite,
        String imageUrl
) {
    public CreateGameDto {
        Objects.requireNonNull(gameName, "Game name must not be null");
        Objects.requireNonNull(gameType, "Game type must not be null");
        Objects.requireNonNull(achievements, "Achievements must not be null");
        Objects.requireNonNull(imageUrl, "Image URL must not be null");
        if (gameName.isBlank()) {
            throw new IllegalArgumentException("Game name must not be empty");
        }
        if (gameType.isBlank()) {
            throw new IllegalArgumentException("Game type must not be empty");
        }
        if (achievements.isEmpty()) {
            throw new IllegalArgumentException("Achievements must not be empty");
        }
        if (imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL must not be empty");
        }
    }
}
