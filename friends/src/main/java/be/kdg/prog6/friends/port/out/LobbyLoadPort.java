package be.kdg.prog6.friends.port.out;
import be.kdg.prog6.friends.adapters.out.entity.LobbyJpaEntity;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.domain.LobbyId;

import java.util.Optional;
import java.util.UUID;

public interface LobbyLoadPort {

    Lobby loadLobbies(UUID playerId);
    Lobby loadLobbyById(LobbyId lobbyId);
}
