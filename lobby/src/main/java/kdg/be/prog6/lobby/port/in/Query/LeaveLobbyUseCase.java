package kdg.be.prog6.lobby.port.in.Query;


import kdg.be.prog6.lobby.port.in.command.LeaveLobbyCommand;

public interface LeaveLobbyUseCase {



    LobbyUpdateQuery leaveLobby(LeaveLobbyCommand command);




}
