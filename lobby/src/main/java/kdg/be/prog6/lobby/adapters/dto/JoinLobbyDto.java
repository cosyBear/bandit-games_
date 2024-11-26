package kdg.be.prog6.lobby.adapters.dto;

import java.util.UUID;

public record JoinLobbyDto(UUID lobbyId , UUID guestId) {
}
