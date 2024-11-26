package kdg.be.prog6.port.in.Query;


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