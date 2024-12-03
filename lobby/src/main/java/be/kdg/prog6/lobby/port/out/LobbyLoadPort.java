package be.kdg.prog6.lobby.port.out;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.RequestAccess;
import be.kdg.prog6.lobby.domain.ids.LobbyId;

import java.util.List;
import java.util.UUID;

public interface LobbyLoadPort {

    Lobby loadLobbyById(LobbyId lobbyId);

    List<RequestAccess> loadRequestAccessForLobby(LobbyId lobbyId);

}
