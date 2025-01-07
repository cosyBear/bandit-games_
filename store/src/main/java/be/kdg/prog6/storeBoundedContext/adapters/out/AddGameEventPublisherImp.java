package be.kdg.prog6.storeBoundedContext.adapters.out;

import be.kdg.prog6.common.events.AddGameToLibraryEvent;
import be.kdg.prog6.storeBoundedContext.port.in.command.AddGameToLibraryCommand;
import be.kdg.prog6.storeBoundedContext.port.out.AddGameEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@Slf4j
public class AddGameEventPublisherImp implements AddGameEventPublisher {



    private final RabbitTemplate rabbitTemplate;

    private static final String addGameExchange = "AddGameExchange";
    private static final String addGameQueue = "AddGameQueue";
    private static final String addGameRoutingKey = "addGame";

    public AddGameEventPublisherImp(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    @Override
    public void publishAddGameToLibrary(AddGameToLibraryEvent eventList) {

        log.info("games are being added to the player library ");
        rabbitTemplate.convertAndSend(addGameExchange , addGameRoutingKey , eventList);

    }
}
