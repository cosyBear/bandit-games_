package kdg.be.prog6.lobby.port.in.command;

import java.util.UUID;

public record CreateLobbyCommand(UUID gameID, UUID lobbyAdminId) {
}
