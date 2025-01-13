package be.kdg.prog6.lobby.port.in.command;

import java.util.UUID;

public record StartGameCommand(UUID lobbyId) {
}
