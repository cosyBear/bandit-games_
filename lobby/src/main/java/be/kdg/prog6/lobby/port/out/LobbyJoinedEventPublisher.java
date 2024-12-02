package be.kdg.prog6.lobby.port.out;

import be.kdg.prog6.common.events.LobbyJoinedEvent;

public interface LobbyJoinedEventPublisher {

    void broadcastPlayerJoinedLobby(LobbyJoinedEvent command);
}
