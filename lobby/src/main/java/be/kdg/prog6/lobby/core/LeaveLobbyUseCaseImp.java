package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.port.in.Query.LeaveLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.command.LeaveLobbyCommand;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import be.kdg.prog6.lobby.util.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LeaveLobbyUseCaseImp implements LeaveLobbyUseCase {


    private final LobbyLoadPort lobbyLoadPort;
    private final LobbySavePort lobbySavePort;

    public LeaveLobbyUseCaseImp(LobbyLoadPort lobbyLoadPort, LobbySavePort lobbySavePort) {
        this.lobbyLoadPort = lobbyLoadPort;
        this.lobbySavePort = lobbySavePort;
    }


    @Override
    @Transactional
    public LobbyUpdateQuery leaveLobby(LeaveLobbyCommand command) {

        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        lobby.leaveLobby(command.guestPlayerId());

        lobbySavePort.save(lobby);

        return Mapper.mapToUpdateQuery(lobby);
    }
}
