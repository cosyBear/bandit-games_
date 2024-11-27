package be.kdg.prog6.lobby.port.in.command;

import be.kdg.prog6.lobby.domain.ids.LobbyId;

import java.util.UUID;

public record LeaveLobbyCommand(LobbyId lobbyId , UUID guestPlayerId) {
}
