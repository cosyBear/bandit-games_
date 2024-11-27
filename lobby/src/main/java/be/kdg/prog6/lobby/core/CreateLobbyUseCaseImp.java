package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.CreateLobbyUseCase;
import be.kdg.prog6.lobby.port.in.LobbyCreatedEvent;
import be.kdg.prog6.lobby.port.in.Query.LobbyCreateQuery;
import be.kdg.prog6.lobby.port.in.command.CreateLobbyCommand;
import be.kdg.prog6.lobby.port.out.LobbyEventPublisher;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CreateLobbyUseCaseImp implements CreateLobbyUseCase {


    private final LobbySavePort lobbySavePort;

    private final LobbyEventPublisher lobbyEventPublisher;

    public CreateLobbyUseCaseImp(LobbySavePort lobbySavePort, LobbyEventPublisher lobbyEventPublisher) {
        this.lobbySavePort = lobbySavePort;
        this.lobbyEventPublisher = lobbyEventPublisher;
    }


    @Override
    public LobbyCreateQuery createLobby(CreateLobbyCommand lobbyCommand) {
        Lobby lobby = Lobby.createLobby(new LobbyId(UUID.randomUUID()), lobbyCommand.gameID(), lobbyCommand.lobbyAdminId());

        log.info("lobby created: {}", lobby.toString());

        lobbySavePort.save(lobby);

        lobbyEventPublisher.publishLobbyCreatedEvent(new LobbyCreatedEvent(lobby.getLobbyId().lobbyId(), lobby.getGameId(), lobby.getLobbyAdmin()));

        return new LobbyCreateQuery(lobby.getLobbyId().lobbyId(), lobby.getGameId(), lobby.getLobbyAdmin());

    }

}
