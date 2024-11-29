package be.kdg.prog6.friends.adapters.in;

import be.kdg.prog6.common.domain.LobbyStatus;
import be.kdg.prog6.friends.adapters.dto.LobbyCreatedDto;
import be.kdg.prog6.friends.domain.LobbyId;
import be.kdg.prog6.friends.port.in.command.LobbyCreatedCommand;
import be.kdg.prog6.friends.port.in.lobby.LobbyCreatedUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LobbyCreatedAmpAdapter {

    private final LobbyCreatedUseCase lobbyCreatedUseCase;

    @RabbitListener(queues = "LobbyCreatedQueue")
    public void LobbyCreated(LobbyCreatedDto dto) {


        LobbyCreatedCommand command = new LobbyCreatedCommand(new LobbyId(dto.lobbyId()), dto.gameId(), dto.playerId(), LobbyStatus.valueOf(dto.lobbyStatus()));
        log.info("lobby event received  : {}", command);
        lobbyCreatedUseCase.lobbyCreated(command);

    }
}
