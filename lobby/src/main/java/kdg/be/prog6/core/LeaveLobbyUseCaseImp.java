package kdg.be.prog6.core;


import kdg.be.prog6.domain.Lobby;
import kdg.be.prog6.port.in.Query.LeaveLobbyUseCase;
import kdg.be.prog6.port.in.Query.LobbyUpdateQuery;
import kdg.be.prog6.port.in.command.LeaveLobbyCommand;
import kdg.be.prog6.port.out.LobbyLoadPort;
import kdg.be.prog6.port.out.LobbySavePort;
import kdg.be.prog6.util.Mapper;
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
    public LobbyUpdateQuery leaveLobby(LeaveLobbyCommand command) {

        Lobby lobby = lobbyLoadPort.loadLobbyById(command.lobbyId());

        lobby.leaveLobby(command.guestPlayerId());

        lobbySavePort.save(lobby);

        return Mapper.mapToUpdateQuery(lobby);
    }
}
