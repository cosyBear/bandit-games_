package kdg.be.prog6.adapters.dto;


import java.util.UUID;

public record LeaveLobbyDto(UUID lobbyId, UUID guestPlayerId) {
}
