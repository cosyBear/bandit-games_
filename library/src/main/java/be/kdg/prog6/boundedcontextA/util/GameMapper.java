package be.kdg.prog6.boundedcontextA.util;


import be.kdg.prog6.boundedcontextA.adapters.in.dto.CreateGameDto;
import be.kdg.prog6.boundedcontextA.adapters.out.Entity.AchievementEntity;
import be.kdg.prog6.boundedcontextA.adapters.out.Entity.GameEntity;
import be.kdg.prog6.boundedcontextA.adapters.out.Entity.GameTypeEntity;
import be.kdg.prog6.boundedcontextA.domain.Achievement;
import be.kdg.prog6.boundedcontextA.domain.Game;
import be.kdg.prog6.boundedcontextA.domain.GameType;
import be.kdg.prog6.boundedcontextA.domain.id.AchievementId;
import be.kdg.prog6.boundedcontextA.domain.id.GameId;
import be.kdg.prog6.boundedcontextA.port.in.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameMapper {

    public static GameQuery toQuery(Game game) {
        List<AchievementQuery> achievementQueryList = game.getAchievementList().stream()
                .map(item -> new AchievementQuery(
                        item.getAchievementName(),
                        item.getAchievementDescription(),
                        item.getImageUrl(),
                        item.isAchieved()))
                .toList();

        return new GameQuery(
                game.getGameName(),
                game.getGameType(),
                achievementQueryList,
                game.isFavourite(),
                game.getImageUrl()
        );
    }


    public static GameEntity mapToEntity(Game game) {
        List<AchievementEntity> achievementEntities = game.getAchievementList().stream()
                .map(item -> new AchievementEntity(
                        item.getAchievementId().achievementId(),
                        item.getAchievementName(),
                        item.getAchievementDescription(),
                        item.getImageUrl(),
                        item.isAchieved()))
                .collect(Collectors.toList());


        return new GameEntity(
                game.getGameId().gameId(),
                game.getGameName(),
                GameTypeEntity.valueOf(game.retrieveGameType().toString()),
                achievementEntities,
                game.getImageUrl(),
                game.isFavourite()
        );

    }

    public static Game mapToDomain(GameEntity entity) {
        List<Achievement> achievements = new ArrayList<>();
        entity.getAchievementList().forEach(item ->
                achievements.add(new Achievement(
                        new AchievementId(item.getAchievementId()),
                        item.getAchievementName(),
                        item.getAchievementDescription(),
                        item.getImageUrl(),
                        item.isAchieved()))
        );
        return new Game(
                new GameId(entity.getGameId()),
                entity.getGameName(),
                GameType.valueOf(entity.getGameType().toString()),
                achievements,
                entity.getImageUrl(),
                entity.isFavourite()
        );
    }


    public static Game mapToDomainFromCommand(CreateGameCommand createGameCommand) {
        List<Achievement> achievementList = createGameCommand.achievementCommandList().stream()
                .map(item -> new Achievement(
                        item.achievementName(),
                        item.achievementDescription(),
                        item.imageUrl(),
                        item.achieved()))
                .collect(Collectors.toList());

        return new Game(
                new GameId(UUID.randomUUID()),
                createGameCommand.gameName(),
                GameType.valueOf(createGameCommand.gameType()),
                achievementList,
                createGameCommand.imageUrl(),
                createGameCommand.favourite());
    }

    public static CreateGameCommand toCommand(CreateGameDto dto) {
        List<AchievementCommand> achievementCommands = dto.achievements().stream()
                .map(achievementDto -> new AchievementCommand(
                        achievementDto.achievementName(),
                        achievementDto.achievementDescription(),
                        achievementDto.imageUrl(),
                        achievementDto.achieved()
                ))
                .toList();

        return new CreateGameCommand(
                dto.gameName(),
                dto.gameType(),
                achievementCommands,
                dto.favourite(),
                dto.imageUrl()
        );
    }



}

