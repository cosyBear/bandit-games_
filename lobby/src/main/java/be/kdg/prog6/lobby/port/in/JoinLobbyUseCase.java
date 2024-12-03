package be.kdg.prog6.lobby.port.in;


import be.kdg.prog6.lobby.port.in.command.CreateRequestAccessCommand;
import be.kdg.prog6.lobby.port.in.command.RequestAccessCommand;

public interface JoinLobbyUseCase
{

        String requestAccessToJoinLobby(RequestAccessCommand command);

}
