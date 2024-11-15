package be.kdg.prog6.boundedcontextA.port.out;

import be.kdg.prog6.boundedcontextA.domain.Game;
import be.kdg.prog6.boundedcontextA.domain.GameType;

import java.util.List;

public interface GameLoadPort {

    List<Game> fetchAvailableGames();

    Game fetchGameByName(String name);

    List<Game> fetchGamesByCategory(GameType category);


}
