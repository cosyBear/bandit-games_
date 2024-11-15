package be.kdg.prog6.boundedcontextA.port.out;

import be.kdg.prog6.boundedcontextA.domain.Game;

import java.util.List;

public interface GameSavePort  {
    void SaveGame(Game game );
    void saveListOfGames(List<Game> gamesList);

}
