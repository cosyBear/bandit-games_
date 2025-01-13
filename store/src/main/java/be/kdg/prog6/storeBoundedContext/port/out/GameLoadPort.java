package be.kdg.prog6.storeBoundedContext.port.out;


import be.kdg.prog6.storeBoundedContext.domain.Game;
import be.kdg.prog6.storeBoundedContext.domain.Store;
import be.kdg.prog6.storeBoundedContext.domain.id.GameId;

import java.util.UUID;

public interface GameLoadPort
{

    Store loadAllAvailGames();

    Game loadGameById(GameId gameId);
}
