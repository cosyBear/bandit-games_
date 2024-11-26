package be.kdg.prog6.friends.port.in.Query;

import java.util.UUID;

public record LoadLobbiesQuery(UUID lobbyId, UUID gameId, UUID playerId){
}
