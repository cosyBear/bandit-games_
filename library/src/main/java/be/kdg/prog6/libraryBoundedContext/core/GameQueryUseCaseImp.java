package be.kdg.prog6.libraryBoundedContext.core;
import be.kdg.prog6.common.events.util.GameNotFoundException;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.port.in.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.FetchGamesByNameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GetGamesByCategoryQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.RetrieveAllGamesQuery;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.util.GameMapper;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameQueryUseCase;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameQueryUseCaseImp implements GameQueryUseCase {


    private final LibraryLoadPort libraryLoadPort;

    private static final Logger logger = LogManager.getLogger(GameQueryUseCaseImp.class);

    public GameQueryUseCaseImp(LibraryLoadPort libraryLoadPort) {
        this.libraryLoadPort = libraryLoadPort;
    }


    @Override
    @Transactional
    public List<GameQuery> getAllAvailableGame(RetrieveAllGamesQuery query) {
        return libraryLoadPort.fetchLibraryWithAllAvailableGames(query.playerId()).getGames().stream()
                .map(GameMapper::toQuery)
                .toList();
    }

    @Override
    @Transactional
    public List<GameQuery> fetchGamesByName(FetchGamesByNameQuery query) {
        Library library = libraryLoadPort.fetchLibraryWithGamesByNamePattern(query.playerId(), query.gameName());
        if (library == null || library.getGames().isEmpty()) {
            throw new GameNotFoundException("No games found matching: " + query.gameName());
        }

        return library.getGames().stream()
                .map(GameMapper::toQuery)
                .toList();
    }


    @Override
    @Transactional
    public List<GameQuery> getGamesByCategory(GetGamesByCategoryQuery query) {
        Library library  = libraryLoadPort.fetchLibraryWithGamesByCategory(query.playerId(), query.category());
        return library.getGames().stream()
                .map(GameMapper::toQuery)
                .toList();
    }


}
