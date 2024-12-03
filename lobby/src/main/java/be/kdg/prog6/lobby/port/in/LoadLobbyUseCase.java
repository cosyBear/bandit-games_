package be.kdg.prog6.lobby.port.in;

import be.kdg.prog6.lobby.domain.Lobby;

import java.util.UUID;

public interface LoadLobbyUseCase {
    Lobby findLobbyById(UUID id);
}
