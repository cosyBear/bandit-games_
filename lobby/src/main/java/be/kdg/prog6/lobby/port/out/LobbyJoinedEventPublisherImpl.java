package be.kdg.prog6.lobby.port.out;

import be.kdg.prog6.common.events.LobbyJoinedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LobbyJoinedEventPublisherImpl implements LobbyJoinedEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String joinLobbyExchange = "JOINLobbyExchange";
    private static final String routingKey = "joinLobby";

    @Override
    public void broadcastPlayerJoinedLobby(LobbyJoinedEvent command) {
        log.info("notifying that the player joined the lobby ");
        rabbitTemplate.convertAndSend(joinLobbyExchange, routingKey, command);
        log.info("the event is send ");


    }
}
