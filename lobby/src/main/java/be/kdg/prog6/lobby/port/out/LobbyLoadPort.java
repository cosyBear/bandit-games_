package be.kdg.prog6.lobby.port.out;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.ids.LobbyId;

public interface LobbyLoadPort {

    Lobby loadLobbyById(LobbyId lobbyId);
}
