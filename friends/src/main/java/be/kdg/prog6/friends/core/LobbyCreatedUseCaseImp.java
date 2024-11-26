package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.port.in.lobby.LobbyCreatedUseCase;
import be.kdg.prog6.friends.port.in.command.LobbyCreatedCommand;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import be.kdg.prog6.friends.port.out.LobbySavePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class LobbyCreatedUseCaseImp implements LobbyCreatedUseCase {


    private final LobbyLoadPort lobbyLoadPort;
    private final LobbySavePort lobbySavePort;

    public LobbyCreatedUseCaseImp(LobbyLoadPort lobbyLoadPort, LobbySavePort lobbySavePort) {
        this.lobbyLoadPort = lobbyLoadPort;
        this.lobbySavePort = lobbySavePort;
    }


    @Override
    @Transactional
    public void lobbyCreated(LobbyCreatedCommand command) {

        Lobby lobby = new Lobby(command.lobbyId(), command.gameId(), command.playerId());

        lobbySavePort.save(lobby);

    }


}
