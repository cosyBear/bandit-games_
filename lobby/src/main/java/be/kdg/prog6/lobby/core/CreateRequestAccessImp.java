package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.port.in.CreateRequestAccessUseCase;
import be.kdg.prog6.lobby.port.in.command.CreateRequestAccessCommand;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class CreateRequestAccessImp implements CreateRequestAccessUseCase {


    private final LobbyLoadPort lobbyLoadPort;
    private final LobbySavePort lobbySavePort;

    @Override
    @Transactional
    public String createRequest(CreateRequestAccessCommand command) {

        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        lobby.createRequestAccess(command.playerId());

        lobbySavePort.save(lobby);

        log.info("the Request has be made Waiting for a Response from the lobby admin .................");

        return "the Request has be made Waiting for a Response from the lobby admin";
    }
}
