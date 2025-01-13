package be.kdg.prog6.libraryBoundedContext.adapters.out;


import be.kdg.prog6.common.events.AddGameToLibraryEvent;
import be.kdg.prog6.libraryBoundedContext.domain.Achievement;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.domain.id.AchievementId;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.AddGameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component

public class AddGameAmpAdapter {


    private final GameUseCase gameUseCase;

    public AddGameAmpAdapter(GameUseCase gameUseCase) {
        this.gameUseCase = gameUseCase;
    }


    @RabbitListener(queues = "AddGameQueue")
    public void addGameToPlayerLibrary(AddGameToLibraryEvent event) {


        log.info("list of games Received to add to the player library ");

        PlayerId playerId = new PlayerId(event.PlayerId());

        Game game = null;
        List<Achievement> achievements = event.gameEvent().achievementList().stream().map(achievementEvent -> {
            return new Achievement(
                    new AchievementId(achievementEvent.achievementId()),
                    achievementEvent.achievementName(),
                    achievementEvent.achievementDescription(),
                    achievementEvent.imageUrl(),
                    achievementEvent.achieved(),
                    new GameId(event.gameEvent().id())

            );
        }).toList();

        game = new Game(
                new GameId(event.gameEvent().id()),
                event.gameEvent().gameName(),
                event.gameEvent().description(),
                GameType.valueOf(event.gameEvent().gameType()),
                achievements,
                event.gameEvent().favourite(),
                event.gameEvent().imageUrl(),
                event.gameEvent().backgroundImageUrl()
        );

        AddGameCommand addGameCommand = new AddGameCommand(game, playerId);

        gameUseCase.addGameToPlayerLibrary(addGameCommand);


    }


}
