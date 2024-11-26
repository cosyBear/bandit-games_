package kdg.be.prog6.port.in.command;


import kdg.be.prog6.domain.ids.LobbyId;

import java.util.UUID;

public record LeaveLobbyCommand(LobbyId lobbyId , UUID guestPlayerId) {
}
