package kdg.be.prog6.lobby.adapters.dto;

import java.util.UUID;

public record CreateLobbyDto(UUID gameID, UUID lobbyAdminId ) {
}
