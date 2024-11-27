package be.kdg.prog6.lobby.port.in.Query;


import be.kdg.prog6.lobby.domain.LobbyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record LobbyUpdateQuery(
        UUID lobbyId,
        UUID gameId,
        UUID lobbyAdmin,
        UUID guestPlayer,
        LocalDateTime createdAt,
        String lobbyStatus
) {
}