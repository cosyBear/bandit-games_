package be.kdg.prog6.libraryBoundedContext.port.in.game;

import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.FetchGamesByNameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GetGamesByCategoryQuery;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.RetrieveAllGamesQuery;

import java.util.List;
import java.util.UUID;

public interface GameQueryUseCase {

    List<GameQuery> getAllAvailableGame(RetrieveAllGamesQuery query  );
    List<GameQuery> fetchGamesByName(FetchGamesByNameQuery query);
    List<GameQuery> getGamesByCategory(GetGamesByCategoryQuery query);

    GameQuery findGameById(UUID gameId);
}
