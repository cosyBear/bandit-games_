package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.port.in.JoinLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.command.AddGuestToLobbyCommand;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import be.kdg.prog6.lobby.util.Mapper;
import org.springframework.stereotype.Service;

@Service
public class JoinLobbyUseCaseImp implements JoinLobbyUseCase {


    private final LobbyLoadPort lobbyLoadPort;
    private final LobbySavePort lobbySavePort;

    public JoinLobbyUseCaseImp(LobbyLoadPort lobbyLoadPort, LobbySavePort lobbySavePort) {
        this.lobbyLoadPort = lobbyLoadPort;
        this.lobbySavePort = lobbySavePort;
    }


    @Override
    public LobbyUpdateQuery addGuestToLobby(AddGuestToLobbyCommand command) {

        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        lobby.addGuestPlayerToLobby(command.guestPlayerId());

        lobbySavePort.save(lobby);

        return Mapper.mapToUpdateQuery(lobby);
    }
}
