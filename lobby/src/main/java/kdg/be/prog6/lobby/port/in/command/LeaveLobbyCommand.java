package kdg.be.prog6.lobby.port.in.command;


import kdg.be.prog6.lobby.domain.ids.LobbyId;

import java.util.UUID;

public record LeaveLobbyCommand(LobbyId lobbyId , UUID guestPlayerId) {
}
