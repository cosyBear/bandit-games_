package kdg.be.prog6.port.in;


import kdg.be.prog6.port.in.Query.LobbyCreateQuery;
import kdg.be.prog6.port.in.command.CreateLobbyCommand;

public interface CreateLobbyUseCase {

    public LobbyCreateQuery createLobby(CreateLobbyCommand lobbyCommand);

}
