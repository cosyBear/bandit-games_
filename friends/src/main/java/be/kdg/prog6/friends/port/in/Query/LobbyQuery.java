package be.kdg.prog6.friends.port.in.Query;

import java.util.UUID;

public record LobbyQuery(UUID lobbyId, UUID gameId , UUID playerId) {
}
