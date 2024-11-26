package kdg.be.prog6.lobby.core;


import kdg.be.prog6.lobby.domain.Lobby;
import kdg.be.prog6.lobby.domain.ids.LobbyId;
import kdg.be.prog6.lobby.port.in.CreateLobbyUseCase;
import kdg.be.prog6.lobby.port.in.Query.LobbyCreateQuery;
import kdg.be.prog6.lobby.port.in.command.CreateLobbyCommand;
import kdg.be.prog6.lobby.port.out.LobbySavePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class CreateLobbyUseCaseImp implements CreateLobbyUseCase {


    private final LobbySavePort lobbySavePort;

    public CreateLobbyUseCaseImp(LobbySavePort lobbySavePort) {
        this.lobbySavePort = lobbySavePort;
    }





    @Override
    public LobbyCreateQuery createLobby(CreateLobbyCommand lobbyCommand) {
        Lobby lobby = Lobby.createLobby(new LobbyId(UUID.randomUUID()), lobbyCommand.gameID(), lobbyCommand.lobbyAdminId());
//        log.f("lobby created: {}", lobby.toString());
        lobbySavePort.save(lobby);

        return new LobbyCreateQuery(lobby.getLobbyId().lobbyId(), lobby.getGameId(), lobby.getLobbyAdmin());

    }

}
