package be.kdg.prog6.storeBoundedContext.port.in.store;


import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import be.kdg.prog6.storeBoundedContext.port.in.gameQuery.GameQuery;

import java.util.List;

public interface LoadStoreUseCase {



    List<GameQuery> viewAllGames();


    GameQuery viewGameDetails(GameId gameId);
}
