package be.kdg.prog6.lobby.port.in.Query;

import java.util.UUID;

public record StartGameQuery(UUID player1, UUID player2, UUID lobbyId) {
}
