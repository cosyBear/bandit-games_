package be.kdg.prog6.libraryBoundedContext.port.out;

import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;

import java.util.List;

public interface GameLoadPort {

    List<Game> fetchAvailableGames();

    Game fetchGameByName(String name);

    List<Game> fetchGamesByCategory(GameType category);


}
