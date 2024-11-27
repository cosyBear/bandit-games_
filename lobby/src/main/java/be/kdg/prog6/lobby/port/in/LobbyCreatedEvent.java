package be.kdg.prog6.lobby.port.in;

import java.util.UUID;

public record LobbyCreatedEvent(UUID lobbyId , UUID gameId, UUID playerId) {
}
