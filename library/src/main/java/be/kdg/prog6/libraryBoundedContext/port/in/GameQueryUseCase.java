package be.kdg.prog6.libraryBoundedContext.port.in;

import be.kdg.prog6.libraryBoundedContext.domain.GameType;

import java.util.List;

public interface GameQueryUseCase {

    List<GameQuery> getAllAvailableGame();
    GameQuery getGameByName(final String name);
    List<GameQuery> getGamesByCategory(final GameType category);

}
