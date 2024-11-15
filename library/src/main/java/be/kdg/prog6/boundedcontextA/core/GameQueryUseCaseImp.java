package be.kdg.prog6.boundedcontextA.core;

import be.kdg.prog6.boundedcontextA.GameMapper;
import be.kdg.prog6.boundedcontextA.domain.Game;
import be.kdg.prog6.boundedcontextA.domain.GameType;
import be.kdg.prog6.boundedcontextA.port.in.AchievementQuery;
import be.kdg.prog6.boundedcontextA.port.in.GameQuery;
import be.kdg.prog6.boundedcontextA.port.in.GameQueryUseCase;
import be.kdg.prog6.boundedcontextA.port.out.GameLoadPort;
import be.kdg.prog6.boundedcontextA.port.out.GameSavePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameQueryUseCaseImp implements GameQueryUseCase {


    private final GameLoadPort gameLoadPort;
    private final GameSavePort gameSavePort;

    private static final Logger logger = LogManager.getLogger(GameQueryUseCaseImp.class);

    public GameQueryUseCaseImp(GameLoadPort gameLoadPort, GameSavePort gameSavePort) {
        this.gameLoadPort = gameLoadPort;
        this.gameSavePort = gameSavePort;
    }


    @Override
    public List<GameQuery> getAllAvailableGame() {
        return gameLoadPort.fetchAvailableGames().stream()
                .map(GameMapper::toQuery)
                .toList();
    }

    @Override
    public GameQuery getGameByName(String name) {
        return GameMapper.toQuery(gameLoadPort.fetchGameByName(name));
    }

    @Override
    public List<GameQuery> getGamesByCategory(GameType category) {
        return gameLoadPort.fetchGamesByCategory(category).stream()
                .map(GameMapper::toQuery)
                .toList();
    }


}
