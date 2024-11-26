package kdg.be.prog6.port.in.Query;


import kdg.be.prog6.port.in.command.LeaveLobbyCommand;

public interface LeaveLobbyUseCase {



    LobbyUpdateQuery leaveLobby(LeaveLobbyCommand command);




}
