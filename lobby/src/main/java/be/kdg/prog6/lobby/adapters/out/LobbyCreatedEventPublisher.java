package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.lobby.port.in.LobbyCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LobbyCreatedEventPublisher implements be.kdg.prog6.lobby.port.out.LobbyCreatedEventPublisher {




    private final RabbitTemplate rabbitTemplate;
    private static String LobbyCreatedExchange = "LobbyCreatedExchange";
    private static String LobbyCreatedRoutingKey = "LobbyCreatedRoutingKey";


    @Override
    public void publishLobbyCreatedEvent(LobbyCreatedEvent event) {


        rabbitTemplate.convertAndSend(LobbyCreatedExchange, LobbyCreatedRoutingKey, event);

        log.info("the lobby created  event has ben send ");

    }
}
