package be.kdg.prog6.common.events;

import java.util.UUID;

public record LobbyRequestEvent(UUID playerId, UUID guestId, UUID lobbyId, boolean requestStatus) {
}
