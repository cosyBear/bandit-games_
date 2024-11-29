package be.kdg.prog6.friends.port.in;

import be.kdg.prog6.friends.domain.LobbyId;

public record UpdateLobbyCommand(LobbyId lobbyId , String status ) {
}
