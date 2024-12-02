package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.common.events.LobbyJoinedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LobbyJoinedEventPublisher implements be.kdg.prog6.lobby.port.out.LobbyJoinedEventPublisher {



    private final RabbitTemplate rabbitTemplate;


    private static final String joinLobbyExchange = "JOINLobbyExchange";
    private static final String routingKey = "joinLobby";

    @Override
    public void broadcastPlayerJoinedLobby(LobbyJoinedEvent command) {


        rabbitTemplate.convertAndSend(joinLobbyExchange, routingKey, command);
        log.info("the player joined lobby event is send ");

    }
}
