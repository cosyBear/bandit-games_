package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.lobby.LobbyCreatedUseCase;
import be.kdg.prog6.friends.port.in.command.LobbyCreatedCommand;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import be.kdg.prog6.friends.port.out.LobbySavePort;
import be.kdg.prog6.friends.port.out.PlayerPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LobbyCreatedUseCaseImp implements LobbyCreatedUseCase {


    private final LobbySavePort lobbySavePort;
    private final PlayerPort playerPort;


    @Override
    @Transactional
    public void lobbyCreated(LobbyCreatedCommand command) {

        final Player player = playerPort.findById(command.playerId());

        Lobby lobby = new Lobby(command.lobbyId(), command.gameId(), player);

        lobbySavePort.save(lobby);

    }


}
