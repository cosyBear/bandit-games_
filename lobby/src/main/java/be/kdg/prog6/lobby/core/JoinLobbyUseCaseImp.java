package be.kdg.prog6.lobby.core;

import be.kdg.prog6.common.events.LobbyJoinedEvent;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.port.in.JoinLobbyUseCase;
import be.kdg.prog6.lobby.port.in.command.RequestAccessCommand;
import be.kdg.prog6.lobby.port.out.LobbyJoinedEventPublisher;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinLobbyUseCaseImp implements JoinLobbyUseCase {


    private final LobbyLoadPort lobbyLoadPort;
    private final LobbySavePort lobbySavePort;
    private final LobbyJoinedEventPublisher lobbyJoinedEventPublisher;

    public JoinLobbyUseCaseImp(LobbyLoadPort lobbyLoadPort, LobbySavePort lobbySavePort, LobbyJoinedEventPublisher lobbyJoinedEventPublisher) {
        this.lobbyLoadPort = lobbyLoadPort;
        this.lobbySavePort = lobbySavePort;
        this.lobbyJoinedEventPublisher = lobbyJoinedEventPublisher;
    }


    @Override
    @Transactional
    public String requestAccessToJoinLobby(RequestAccessCommand command) {


        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        String response = lobby.handleRequestAccess(command.playerId(), command.status());

        lobbySavePort.save(lobby);

        if (response.equals("request Accepted")) {
            log.info("{} the player is added to the lobby ", response);

            lobbyJoinedEventPublisher.broadcastPlayerJoinedLobby(new LobbyJoinedEvent(lobby.getLobbyId().lobbyId(), lobby.getLobbyStatus().toString()));

        } else
            log.info("{} the player is not added to the lobby ", response);


        return response;


    }

}
