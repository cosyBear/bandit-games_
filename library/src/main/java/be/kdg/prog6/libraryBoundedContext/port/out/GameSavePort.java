package be.kdg.prog6.libraryBoundedContext.port.out;

import be.kdg.prog6.libraryBoundedContext.domain.Game;


public interface GameSavePort  {
    void saveGame(Game game );
    //void saveListOfGames(List<Game> gamesList); use for later

}
