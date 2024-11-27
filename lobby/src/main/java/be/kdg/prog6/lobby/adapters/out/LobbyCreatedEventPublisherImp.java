package be.kdg.prog6.lobby.adapters.out;


import be.kdg.prog6.lobby.port.in.LobbyCreatedEvent;
import be.kdg.prog6.lobby.port.out.LobbyEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LobbyCreatedEventPublisherImp implements LobbyEventPublisher {


    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "LobbyCreatedExchange";
    private static String RoutingKey = "LobbyCreatedRoutingKey";

    @Override
    public void publishLobbyCreatedEvent(LobbyCreatedEvent event) {

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, RoutingKey, event);

        log.info("lobbyCreated Event Is published  ");

    }
}
