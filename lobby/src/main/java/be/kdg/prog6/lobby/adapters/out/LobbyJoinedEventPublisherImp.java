package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.common.events.LobbyJoinedEvent;
import be.kdg.prog6.lobby.port.out.LobbyJoinedEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class LobbyJoinedEventPublisherImp implements LobbyJoinedEventPublisher {



    private final RabbitTemplate rabbitTemplate;
    private static final String joinLobbyExchange = "JOINLobbyExchange";
    private static final String routingKey = "joinLobby";


    @Override
    public void broadcastPlayerJoinedLobby(LobbyJoinedEvent event) {

        log.info("notifying that the player joined the lobby ");
        rabbitTemplate.convertAndSend(joinLobbyExchange , routingKey, event);
        log.info("the event is send ");


    }
}
