package be.kdg.prog6.lobby.port.in;

import be.kdg.prog6.lobby.port.in.Query.LobbyCreateQuery;
import be.kdg.prog6.lobby.port.in.command.CreateLobbyCommand;

public interface CreateLobbyUseCase {

    public LobbyCreateQuery createLobby(CreateLobbyCommand lobbyCommand);

}
