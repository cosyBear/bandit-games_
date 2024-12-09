package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.LoadLobbyUseCase;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadLobbyUseCaseImpl implements LoadLobbyUseCase {

    private final LobbyLoadPort lobbyLoadPort;
    @Override
    public Lobby findLobbyById(UUID id) {
        return lobbyLoadPort.loadLobbyById(new LobbyId(id));
    }
}
