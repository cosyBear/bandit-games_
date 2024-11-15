package be.kdg.prog6.boundedcontextA.port.out;

import be.kdg.prog6.boundedcontextA.domain.Game;


public interface GameSavePort  {
    void saveGame(Game game );
    //void saveListOfGames(List<Game> gamesList); use for later

}
