package be.kdg.prog6.friends.adapters.in;

import be.kdg.prog6.common.events.LobbyJoinedEvent;
import be.kdg.prog6.friends.domain.LobbyId;
import be.kdg.prog6.friends.port.in.UpdateLobbyCommand;
import be.kdg.prog6.friends.port.in.UpdateLobbyStatusUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LobbyJoinedAmpAdapter {
    private final UpdateLobbyStatusUseCase updateLobbyStatusUseCase;

    @RabbitListener(queues = "JOINLobbyQueue")
    public void joinLobby(LobbyJoinedEvent event){

        UpdateLobbyCommand command  = new UpdateLobbyCommand(new LobbyId(event.lobbyId()), event.Status());

        log.info("updating the lobby");

        updateLobbyStatusUseCase.updateLobbyStatus(command);
    }

}
