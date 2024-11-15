package be.kdg.prog6.boundedcontextA.core;

import be.kdg.prog6.boundedcontextA.GameMapper;
import be.kdg.prog6.boundedcontextA.domain.Game;
import be.kdg.prog6.boundedcontextA.port.in.CreateGameCommand;
import be.kdg.prog6.boundedcontextA.port.in.GameUseCase;
import be.kdg.prog6.boundedcontextA.port.in.GameCommand;
import be.kdg.prog6.boundedcontextA.port.in.GameQuery;
import be.kdg.prog6.boundedcontextA.port.out.GameLoadPort;
import be.kdg.prog6.boundedcontextA.port.out.GameSavePort;
import be.kdg.prog6.common.events.util.DomainException;
import be.kdg.prog6.common.events.util.GameNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class GameUseCaseImp implements GameUseCase {

    private final GameLoadPort gameLoadPort;
    private final GameSavePort gameSavePort;
    private static final Logger logger = LogManager.getLogger(GameUseCaseImp.class);

    public GameUseCaseImp(GameLoadPort gameLoadPort, GameSavePort gameSavePort) {
        this.gameLoadPort = gameLoadPort;
        this.gameSavePort = gameSavePort;
    }

    @Transactional
    @Override
    public GameQuery markGameAsFavourite(GameCommand command) {
        Game game = gameLoadPort.fetchGameByName(command.gameName());
        if (game == null) {
            logger.error("Game not found: {}", command.gameName());
            throw new GameNotFoundException("Game not found with name: " + command.gameName());
        }
        game.markAsFavorite();
        if (!game.isGameMarkedAsFavorite()) {
            logger.error("Failed to mark game as favorite: {}", game.getGameName());
            throw new DomainException("Could not mark game as favorite.");
        }
        gameSavePort.saveGame(game);
        logger.info("Game marked as favorite: {}", game.getGameName());
        return GameMapper.toQuery(game);
    }

    @Override
    @Transactional
    public GameQuery createGame(CreateGameCommand createGameCommand) {
        Game game = GameMapper.mapToDomainFromCommand(createGameCommand);
        gameSavePort.saveGame(game);
        return GameMapper.toQuery(game);
    }


}
