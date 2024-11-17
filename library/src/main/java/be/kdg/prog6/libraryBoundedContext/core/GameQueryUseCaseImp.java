package be.kdg.prog6.libraryBoundedContext.core;
import be.kdg.prog6.libraryBoundedContext.util.GameMapper;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.port.in.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.GameQueryUseCase;
import be.kdg.prog6.libraryBoundedContext.port.out.GameLoadPort;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameQueryUseCaseImp implements GameQueryUseCase {


    private final GameLoadPort gameLoadPort;

    private static final Logger logger = LogManager.getLogger(GameQueryUseCaseImp.class);

    public GameQueryUseCaseImp(GameLoadPort gameLoadPort) {
        this.gameLoadPort = gameLoadPort;

    }


    @Override
    @Transactional
    public List<GameQuery> getAllAvailableGame() {
        return gameLoadPort.fetchAvailableGames().stream()
                .map(GameMapper::toQuery)
                .toList();
    }

    @Override
    @Transactional
    public GameQuery getGameByName(String name) {
        return GameMapper.toQuery(gameLoadPort.fetchGameByName(name));
    }

    @Override
    @Transactional
    public List<GameQuery> getGamesByCategory(GameType category) {
        return gameLoadPort.fetchGamesByCategory(category).stream()
                .map(GameMapper::toQuery)
                .toList();
    }


}
