package kdg.be.prog6.lobby.port.in;


import kdg.be.prog6.lobby.port.in.Query.LobbyCreateQuery;
import kdg.be.prog6.lobby.port.in.command.CreateLobbyCommand;

public interface CreateLobbyUseCase {

    public LobbyCreateQuery createLobby(CreateLobbyCommand lobbyCommand);

}
