package be.kdg.prog6.lobby.port.in.command;

import be.kdg.prog6.lobby.domain.ids.LobbyId;

public record ShowLobbyRequestsQuery(LobbyId lobbyId) {
}
