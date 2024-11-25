package be.kdg.prog6.lobby.port.in.Query;

import be.kdg.prog6.lobby.port.in.command.LeaveLobbyCommand;

public interface LeaveLobbyUseCase {



    LobbyUpdateQuery leaveLobby(LeaveLobbyCommand command);




}
