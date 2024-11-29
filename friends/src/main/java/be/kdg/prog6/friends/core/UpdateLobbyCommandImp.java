package be.kdg.prog6.friends.core;

import be.kdg.prog6.common.domain.LobbyStatus;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.port.in.UpdateLobbyCommand;
import be.kdg.prog6.friends.port.in.UpdateLobbyStatusUseCase;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import be.kdg.prog6.friends.port.out.LobbySavePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateLobbyCommandImp implements UpdateLobbyStatusUseCase {


    private final  LobbySavePort lobbySavePort;
    private final LobbyLoadPort lobbyLoadPort;


    @Override
    public void updateLobbyStatus(UpdateLobbyCommand command) {
        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        lobby.updateLobbyStatus(LobbyStatus.valueOf(command.status()));
        log.info("the lobby is update {}", lobby);
        lobbySavePort.save(lobby);
    }
}
