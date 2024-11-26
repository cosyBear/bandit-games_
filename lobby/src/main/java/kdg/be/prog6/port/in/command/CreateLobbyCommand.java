package kdg.be.prog6.port.in.command;

import java.util.UUID;

public record CreateLobbyCommand(UUID gameID, UUID lobbyAdminId) {
}
