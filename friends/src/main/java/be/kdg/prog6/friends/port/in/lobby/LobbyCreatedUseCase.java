package be.kdg.prog6.friends.port.in.lobby;

import be.kdg.prog6.friends.port.in.command.LobbyCreatedCommand;

public interface LobbyCreatedUseCase {

    void lobbyCreated(LobbyCreatedCommand command);

}
