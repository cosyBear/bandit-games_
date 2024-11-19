package be.kdg.prog6.libraryBoundedContext.core;

import be.kdg.prog6.common.events.util.LibraryNotFoundException;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.port.in.*;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.port.out.LibrarySavePort;
import be.kdg.prog6.libraryBoundedContext.util.GameMapper;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.common.events.util.GameNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class GameUseCaseImp implements GameUseCase {

    private static final Logger logger = LogManager.getLogger(GameUseCaseImp.class);

    private final LibraryLoadPort libraryLoadPort;
    private final LibrarySavePort librarySavePort;

    public GameUseCaseImp(LibraryLoadPort libraryLoadPort, LibrarySavePort librarySavePort) {
        this.libraryLoadPort = libraryLoadPort;
        this.librarySavePort = librarySavePort;
    }

    @Transactional
    @Override
    public GameQuery markGameAsFavourite(GameCommand command) {

        Library library = libraryLoadPort.fetchLibraryWithGamesByNamePattern(command.playerId(), command.gameName());
        if (library == null) {
            logger.error("Library not found or game not found for player: {}", command.playerId());
            throw new GameNotFoundException("Game not found with name: " + command.gameName());
        }

        Game game = library.markGameAsFavorite(new GameId(command.gameId()));

        librarySavePort.save(library);
        logger.info("Game marked as favorite: ");

        return GameMapper.toQuery(game);
    }


    @Transactional
    @Override
    public GameQuery givePlayerAnAchievement(EarnAchievementCommand command) {
        Library library = libraryLoadPort.fetchLibraryWithGamesByNamePattern(command.playerId(), command.gameName());
        if (library == null) {
            logger.error("Library not found for player: {}", command.playerId());
            throw new LibraryNotFoundException("Library not found for player ID: {}" + command.playerId());
        }

        Game game = library.givePlayerAnAchievement(new GameId(command.gameId()), command.AchievementName());

        librarySavePort.save(library);
        return GameMapper.toQuery(game);
    }


    // so the store create the game and the store will send an event to the library context to add the game to the lib

}
