package be.kdg.prog6.lobby.port.in.Query;

import java.util.UUID;

public record LobbyCreateQuery(UUID lobbyId, UUID gameId  ,UUID playerId , String Status) {
}
