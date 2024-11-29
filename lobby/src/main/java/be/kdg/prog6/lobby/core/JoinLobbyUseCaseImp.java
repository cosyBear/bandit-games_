package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.port.in.JoinLobbyUseCase;
import be.kdg.prog6.common.events.LobbyJoinedEvent;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.command.AddGuestToLobbyCommand;
import be.kdg.prog6.lobby.port.out.LobbyJoinedEventPublisher;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import be.kdg.prog6.lobby.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JoinLobbyUseCaseImp implements JoinLobbyUseCase {


    private static final Logger log = LoggerFactory.getLogger(JoinLobbyUseCaseImp.class);
    private final LobbyLoadPort lobbyLoadPort;
    private final LobbySavePort lobbySavePort;
    private final LobbyJoinedEventPublisher lobbyJoinedEventPublisher;

    public JoinLobbyUseCaseImp(LobbyLoadPort lobbyLoadPort, LobbySavePort lobbySavePort, LobbyJoinedEventPublisher lobbyJoinedEventPublisher) {
        this.lobbyLoadPort = lobbyLoadPort;
        this.lobbySavePort = lobbySavePort;
        this.lobbyJoinedEventPublisher = lobbyJoinedEventPublisher;
    }


    @Override
    public LobbyUpdateQuery addGuestToLobby(AddGuestToLobbyCommand command) {
        log.info("the player is being added to the lobby");
        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        lobby.addGuestPlayerToLobby(command.guestPlayerId());

        lobbySavePort.save(lobby);
        log.info("the player is added to the lobby now");

        lobbyJoinedEventPublisher.broadcastPlayerJoinedLobby(new LobbyJoinedEvent(lobby.getLobbyId().lobbyId() , lobby.getLobbyStatus().toString()));
        return Mapper.mapToUpdateQuery(lobby);
    }
}
