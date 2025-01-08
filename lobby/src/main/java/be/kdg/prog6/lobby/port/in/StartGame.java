package be.kdg.prog6.lobby.port.in;


import be.kdg.prog6.lobby.port.in.Query.StartGameQuery;
import be.kdg.prog6.lobby.port.in.command.StartGameCommand;

public interface StartGame {

    StartGameQuery startGame(StartGameCommand startGameCommand);


}
