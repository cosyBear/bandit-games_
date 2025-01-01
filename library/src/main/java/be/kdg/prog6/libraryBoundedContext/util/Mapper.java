package be.kdg.prog6.libraryBoundedContext.util;

import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.adapters.in.dto.CreateGameDto;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.*;
import be.kdg.prog6.libraryBoundedContext.domain.*;
import be.kdg.prog6.libraryBoundedContext.domain.id.AchievementId;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.LibraryId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.AchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.CreateGameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.AchievementQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;

import java.util.List;

public class Mapper {



    public static Library mapDomainLibrary(LibraryEntity entity) {
        if(entity.getLibraryId() != null) {
            List<Game> gameList = entity.getGames().stream()
                    .map(Mapper::mapToDomain)
                    .toList();

            PlayerId playerId = new PlayerId(entity.getPlayerEntity().getPlayerId());
            return new Library(
                    new LibraryId(entity.getLibraryId()),
                    gameList,
                    playerId
            );
        } else {
            return new Library();
        }
    }

    public static LibraryEntity mapEntityLibrary(Library library) {
        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setLibraryId(library.getLibraryId().libraryId());

        // Set games and back-reference to the library
        List<GameEntity> gameEntities = library.getGames().stream()
                .map(game -> {
                    GameEntity gameEntity = mapToEntity(game);
                    gameEntity.setLibraryEntity(libraryEntity); // Set the back-reference
                    return gameEntity;
                })
                .toList();

        libraryEntity.setGames(gameEntities);

        // Set the player reference
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerId(library.getPlayerId().Id());
        playerEntity.setLibrary(libraryEntity); // Set the bidirectional relationship

        libraryEntity.setPlayerEntity(playerEntity);
        return libraryEntity;
    }

    public static GameEntity mapToEntity(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(game.getGameId().id());
        gameEntity.setGameName(game.getGameName());
        gameEntity.setGameType(game.getGameType().name());
        gameEntity.setBackgroundImageUrl(game.getBackgroundImageUrl());
        gameEntity.setImageUrl(game.getImageUrl());
        gameEntity.setFavourite(game.isFavourite());
        gameEntity.setDescription(game.getDescription());

        // Set achievements and their back-reference to the game
        List<AchievementEntity> achievementEntities = game.getAchievementList().stream()
                .map(achievement -> {
                    AchievementEntity achievementEntity = mapToEntity(achievement);
                    achievementEntity.setGameEntity(gameEntity); // Set the back-reference
                    return achievementEntity;
                })
                .toList();

        gameEntity.setAchievementList(achievementEntities);
        return gameEntity;
    }

    public static Game mapToDomain(GameEntity entity) {
        List<Achievement> achievements = entity.getAchievementList().stream()
                .map(Mapper::mapToDomain)
                .toList();

        return new Game(
                new GameId(entity.getGameId()),
                entity.getGameName(),
                entity.getDescription(),
                GameType.valueOf(entity.getGameType()),
                achievements,
                entity.isFavourite(),
                entity.getImageUrl(),
                entity.getBackgroundImageUrl()
        );
    }

    public static Achievement mapToDomain(AchievementEntity entity) {
        return new Achievement(
                new AchievementId(entity.getAchievementId()),
                entity.getAchievementName(),
                entity.getAchievementDescription(),
                entity.getImageUrl(),
                entity.isAchieved(),
                new GameId(entity.getGameEntity().getGameId())
        );
    }

    // Map Achievement (Domain) to AchievementEntity
    public static AchievementEntity mapToEntity(Achievement achievement) {
        AchievementEntity achievementEntity = new AchievementEntity();
        achievementEntity.setAchievementId(achievement.getAchievementId().achievementId());
        achievementEntity.setAchievementName(achievement.getAchievementName());
        achievementEntity.setAchievementDescription(achievement.getAchievementDescription());
        achievementEntity.setImageUrl(achievement.getImageUrl());
        achievementEntity.setAchieved(achievement.isAchieved());
        return achievementEntity;
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
                game.getGameId().id(),
                game.getGameName(),
                game.getDescription(),
                game.getGameType(),
                achievementQueryList,
                game.isFavourite(),
                game.getImageUrl(),
                game.getBackgroundImageUrl()
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
