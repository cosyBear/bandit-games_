package be.kdg.prog6.common.events;

import java.util.UUID;

public record StartGameEvent(UUID playerId, UUID guestId, UUID lobbyId) {
}
