package be.kdg.prog6.lobby.adapters.dto;

import java.util.UUID;

public record RequestAccessDto (UUID LobbyId, UUID guestId , String status  ) {
}
