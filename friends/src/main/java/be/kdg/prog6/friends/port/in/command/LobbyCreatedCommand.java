package be.kdg.prog6.friends.port.in.command;


import be.kdg.prog6.friends.domain.LobbyId;

import java.util.UUID;

public record LobbyCreatedCommand(LobbyId lobbyId, UUID gameId, UUID playerId) {
}
