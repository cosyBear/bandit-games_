package be.kdg.prog6.lobby.port.in;


import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.command.AddGuestToLobbyCommand;

public interface JoinLobbyUseCase
{


        LobbyUpdateQuery addGuestToLobby(AddGuestToLobbyCommand command);

}
