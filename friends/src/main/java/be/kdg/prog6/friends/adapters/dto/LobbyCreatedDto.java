package be.kdg.prog6.friends.adapters.dto;

import java.util.UUID;

public record LobbyCreatedDto(UUID lobbyId , UUID gameId, UUID playerId , String lobbyStatus) {
}
