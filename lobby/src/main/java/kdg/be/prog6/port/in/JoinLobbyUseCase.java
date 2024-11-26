package kdg.be.prog6.port.in;


import kdg.be.prog6.port.in.Query.LobbyUpdateQuery;
import kdg.be.prog6.port.in.command.AddGuestToLobbyCommand;

public interface JoinLobbyUseCase
{


        LobbyUpdateQuery addGuestToLobby(AddGuestToLobbyCommand command);

}
