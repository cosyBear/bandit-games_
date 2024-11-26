package kdg.be.prog6.port.in.Query;

import java.util.UUID;

public record LobbyCreateQuery(UUID lobbyId, UUID gameId  ,UUID playerId) {
}
