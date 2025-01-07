package be.kdg.prog6.lobby.core;


import be.kdg.prog6.common.events.StartGameEvent;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.Query.StartGameQuery;
import be.kdg.prog6.lobby.port.in.StartGame;
import be.kdg.prog6.lobby.port.in.command.StartGameCommand;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.StartGameSsePort;
import jakarta.persistence.Lob;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartGameUseCaseImp implements StartGame {

    private final LobbyLoadPort loadPort;
    private final StartGameSsePort startGameSsePort;


    @Override
    @Transactional
    public StartGameQuery startGame(StartGameCommand startGameCommand) {
        Lobby lobby = loadPort.loadLobbyById(new LobbyId(startGameCommand.lobbyId()));

        StartGameQuery query = new StartGameQuery(lobby.getLobbyAdmin(), lobby.getGuestPlayer(), lobby.getLobbyId().lobbyId());

        startGameSsePort.sendEvent(new StartGameEvent(lobby.getLobbyAdmin(), lobby.getGuestPlayer(), lobby.getLobbyId().lobbyId()));
        log.info("Notifying the guest player that the game has started in lobby: {}", lobby.getLobbyId().lobbyId());
        return query;
    }


}
