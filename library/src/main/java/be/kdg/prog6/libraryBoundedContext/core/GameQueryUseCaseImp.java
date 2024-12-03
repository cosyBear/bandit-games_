package be.kdg.prog6.libraryBoundedContext.core;
import be.kdg.prog6.common.events.util.GameNotFoundException;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.FetchGamesByNameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GetGamesByCategoryQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.RetrieveAllGamesQuery;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.util.Mapper;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameQueryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameQueryUseCaseImp implements GameQueryUseCase {

    private final LibraryLoadPort libraryLoadPort;

    @Override
    @Transactional(readOnly = true)
    public List<GameQuery> getAllAvailableGame(RetrieveAllGamesQuery query) {

        return libraryLoadPort.fetchLibraryWithAllAvailableGames(query.playerId()).getGames().stream()
                .map(Mapper::toQuery)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<GameQuery> fetchGamesByName(FetchGamesByNameQuery query) {
        Library library = libraryLoadPort.fetchLibraryWithGamesByNamePattern(query.playerId(), query.gameName());
        if (library == null || library.getGames().isEmpty()) {
            throw new GameNotFoundException("No games found matching: " + query.gameName());
        }

        return library.getGames().stream()
                .map(Mapper::toQuery)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<GameQuery> getGamesByCategory(GetGamesByCategoryQuery query) {
        Library library  = libraryLoadPort.fetchLibraryWithGamesByCategory(query.playerId(), query.category());
        return library.getGames().stream()
                .map(Mapper::toQuery)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GameQuery findGameById(UUID gameId) {
        final Game game = libraryLoadPort.fetchLibraryWithGameById(gameId).getGames().getFirst();

        return Mapper.toQuery(game);
    }


}
