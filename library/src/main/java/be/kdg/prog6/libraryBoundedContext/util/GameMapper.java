package be.kdg.prog6.libraryBoundedContext.util;

import be.kdg.prog6.libraryBoundedContext.adapters.in.dto.CreateGameDto;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.*;
import be.kdg.prog6.libraryBoundedContext.domain.*;
import be.kdg.prog6.libraryBoundedContext.domain.id.AchievementId;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.LibraryId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.*;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.AchievementQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;

import java.util.List;

public class GameMapper {

    public static Library mapDomainLibrary(LibraryEntity entity) {
        List<Game> gameList = entity.getGames().stream()
                .map(GameMapper::mapToDomain)
                .toList();

        PlayerId playerId = new PlayerId(entity.getPlayer().getPlayerId());
        return new Library(
                new LibraryId(entity.getLibraryId()),
                gameList,
                playerId
        );
    }

    // Map Library (Domain) to LibraryEntity
    public static LibraryEntity mapEntityLibrary(Library library) {
        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setLibraryId(library.getLibraryId().libraryId());

        // Set games and back-reference to the library
        List<GameEntity> gameEntities = library.getGames().stream()
                .map(game -> {
                    GameEntity gameEntity = mapToEntity(game);
                    gameEntity.setLibrary(libraryEntity); // Set the back-reference
                    return gameEntity;
                })
                .toList();

        libraryEntity.setGames(gameEntities);

        // Set the player reference
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerId(library.getPlayerId().Id());
        playerEntity.setLibrary(libraryEntity); // Set the bidirectional relationship

        libraryEntity.setPlayer(playerEntity);
        return libraryEntity;
    }

    // Map Game (Domain) to GameEntity
    public static GameEntity mapToEntity(Game game) {
        List<AchievementEntity> achievementEntities = game.getAchievementList().stream()
                .map(GameMapper::mapToEntity)
                .toList();

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(game.getGameId().gameId());
        gameEntity.setGameName(game.getGameName());
        gameEntity.setGameType(GameTypeEntity.valueOf(game.getGameType().name()));
        gameEntity.setAchievementList(achievementEntities);
        gameEntity.setImageUrl(game.getImageUrl());
        gameEntity.setFavourite(game.isFavourite());

        // IMPORTANT: The `library` field will be set by `mapEntityLibrary`.
        return gameEntity;
    }

    // Map GameEntity to Game (Domain)
    public static Game mapToDomain(GameEntity entity) {
        List<Achievement> achievements = entity.getAchievementList().stream()
                .map(GameMapper::mapToDomain)
                .toList();

        return new Game(
                new GameId(entity.getGameId()),
                entity.getGameName(),
                GameType.valueOf(entity.getGameType().name()),
                achievements,
                entity.getImageUrl(),
                entity.isFavourite()
        );
    }

    // Map AchievementEntity to Achievement (Domain)
    public static Achievement mapToDomain(AchievementEntity entity) {
        return new Achievement(
                new AchievementId(entity.getAchievementId()),
                entity.getAchievementName(),
                entity.getAchievementDescription(),
                entity.getImageUrl(),
                entity.isAchieved()
        );
    }

    // Map Achievement (Domain) to AchievementEntity
    public static AchievementEntity mapToEntity(Achievement achievement) {
        return new AchievementEntity(
                achievement.getAchievementId().achievementId(),
                achievement.getAchievementName(),
                achievement.getAchievementDescription(),
                achievement.getImageUrl(),
                achievement.isAchieved()
        );
    }

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
