package be.kdg.prog6.storeBoundedContext.util;

import be.kdg.prog6.storeBoundedContext.adapters.out.Entity.AchievementEntity;
import be.kdg.prog6.storeBoundedContext.adapters.out.Entity.GameEntity;
import be.kdg.prog6.storeBoundedContext.adapters.out.Entity.SystemRequirementsEntity;
import be.kdg.prog6.storeBoundedContext.domain.Achievement;
import be.kdg.prog6.storeBoundedContext.domain.Game;
import be.kdg.prog6.storeBoundedContext.domain.GameType;
import be.kdg.prog6.storeBoundedContext.domain.SystemRequirements;
import be.kdg.prog6.storeBoundedContext.domain.id.AchievementId;
import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import be.kdg.prog6.storeBoundedContext.port.in.gameQuery.AchievementQuery;
import be.kdg.prog6.storeBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.storeBoundedContext.port.in.gameQuery.SystemRequirementsQuery;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomMapper {

    // Convert Game to GameEntity
    public static GameEntity toGameEntity(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(game.getGameId().id());
        gameEntity.setGameName(game.getGameName());
        gameEntity.setGameType(game.getGameType().name());
        gameEntity.setImageUrl(game.getImageUrl());
        gameEntity.setBackgroundImageUrl(game.getBackgroundImageUrl());
        gameEntity.setDescription(game.getDescription());
        gameEntity.setPrice(game.getPrice());
        gameEntity.setRating(game.getRating());

        List<AchievementEntity> achievementEntities = game.getAchievementList().stream()
                .map(achievement -> toAchievementEntity(achievement, gameEntity))
                .collect(Collectors.toList());
        gameEntity.setAchievementList(achievementEntities);

        return gameEntity;
    }

    public static AchievementEntity toAchievementEntity(Achievement achievement, GameEntity gameEntity) {
        AchievementEntity achievementEntity = new AchievementEntity();
        achievementEntity.setAchievementId(achievement.getAchievementId().achievementId());
        achievementEntity.setAchievementName(achievement.getAchievementName());
        achievementEntity.setAchievementDescription(achievement.getAchievementDescription());
        achievementEntity.setImageUrl(achievement.getImageUrl());
        achievementEntity.setGameEntity(gameEntity);
        return achievementEntity;
    }

    // Convert SystemRequirements to SystemRequirementsEntity
    public static SystemRequirementsEntity toSystemRequirementsEntity(SystemRequirements systemRequirements) {
        return new SystemRequirementsEntity(
                systemRequirements.getMinimumOperatingSystem(),
                systemRequirements.getMinimumProcessor(),
                systemRequirements.getMinimumMemoryInGB(),
                systemRequirements.getMinimumGraphicsCard(),
                systemRequirements.getMinimumStorageInGB(),
                systemRequirements.getMinimumDirectXVersion(),
                systemRequirements.getRecommendedOperatingSystem(),
                systemRequirements.getRecommendedProcessor(),
                systemRequirements.getRecommendedMemoryInGB(),
                systemRequirements.getRecommendedGraphicsCard(),
                systemRequirements.getRecommendedStorageInGB(),
                systemRequirements.getRecommendedDirectXVersion()
        );
    }

    // Convert GameEntity to Game
    public static Game toGame(GameEntity gameEntity) {
        Game game = new Game(
                new GameId(gameEntity.getGameId()),
                gameEntity.getGameName(),
                GameType.valueOf(gameEntity.getGameType()),
                gameEntity.getImageUrl(),
                gameEntity.getBackgroundImageUrl(),
                gameEntity.getDescription(),
                gameEntity.getPrice(),
                gameEntity.getRating()
        );

        List<Achievement> achievements = gameEntity.getAchievementList().stream()
                .map(CustomMapper::toAchievement)
                .collect(Collectors.toList());
        game.setAchievementList(achievements);

        return game;
    }

    // Convert AchievementEntity to Achievement
    public static Achievement toAchievement(AchievementEntity achievementEntity) {
        return new Achievement(
                new AchievementId(achievementEntity.getAchievementId()),
                achievementEntity.getAchievementName(),
                achievementEntity.getAchievementDescription(),
                achievementEntity.getImageUrl()
        );
    }

    public static SystemRequirements toSystemRequirements(SystemRequirementsEntity systemRequirementsEntity) {
        return new SystemRequirements(
                systemRequirementsEntity.getMinimumOperatingSystem(),
                systemRequirementsEntity.getMinimumProcessor(),
                systemRequirementsEntity.getMinimumMemoryInGB(),
                systemRequirementsEntity.getMinimumGraphicsCard(),
                systemRequirementsEntity.getMinimumStorageInGB(),
                systemRequirementsEntity.getMinimumDirectXVersion(),
                systemRequirementsEntity.getRecommendedOperatingSystem(),
                systemRequirementsEntity.getRecommendedProcessor(),
                systemRequirementsEntity.getRecommendedMemoryInGB(),
                systemRequirementsEntity.getRecommendedGraphicsCard(),
                systemRequirementsEntity.getRecommendedStorageInGB(),
                systemRequirementsEntity.getRecommendedDirectXVersion()
        );
    }

    // Convert Game to GameQuery
    public static GameQuery toGameQuery(Game game) {
        GameQuery gameQuery = new GameQuery();
        gameQuery.setId(game.getGameId().id());
        gameQuery.setGameName(game.getGameName());
        gameQuery.setDescription(game.getDescription());
        gameQuery.setGameType(game.getGameType());
        gameQuery.setImageUrl(game.getImageUrl());
        gameQuery.setBackgroundImageUrl(game.getBackgroundImageUrl());
        gameQuery.setPrice(game.getPrice());
        gameQuery.setRating(game.getRating());

        List<AchievementQuery> achievementQueries = game.getAchievementList().stream()
                .map(CustomMapper::toAchievementQuery)
                .collect(Collectors.toList());
        gameQuery.setAchievementList(achievementQueries);

        return gameQuery;
    }

    private static AchievementQuery toAchievementQuery(Achievement achievement) {
        return new AchievementQuery(
                achievement.getAchievementName(),
                achievement.getAchievementDescription(),
                achievement.getImageUrl()
        );
    }

    private static SystemRequirementsQuery toSystemRequirementsQuery(SystemRequirements systemRequirements) {
        return new SystemRequirementsQuery(
                systemRequirements.getMinimumOperatingSystem(),
                systemRequirements.getMinimumProcessor(),
                systemRequirements.getMinimumMemoryInGB(),
                systemRequirements.getMinimumGraphicsCard(),
                systemRequirements.getMinimumStorageInGB(),
                systemRequirements.getMinimumDirectXVersion(),
                systemRequirements.getRecommendedOperatingSystem(),
                systemRequirements.getRecommendedProcessor(),
                systemRequirements.getRecommendedMemoryInGB(),
                systemRequirements.getRecommendedGraphicsCard(),
                systemRequirements.getRecommendedStorageInGB(),
                systemRequirements.getRecommendedDirectXVersion()
        );
    }
}
