package be.kdg.prog6.storeBoundedContext.adapters.in.dto;


import java.util.List;
import java.util.Objects;

public record CreateGameDto(
        String gameName,
        String gameType,
        List<AchievementDto> achievements,
        String imageUrl,
        String backgroundImageUrl,
        String description,
        double  price)
{

}
