package kdg.be.prog6.lobby.port.in;


import kdg.be.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import kdg.be.prog6.lobby.port.in.command.AddGuestToLobbyCommand;

public interface JoinLobbyUseCase
{


        LobbyUpdateQuery addGuestToLobby(AddGuestToLobbyCommand command);

}
