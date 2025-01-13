package be.kdg.prog6.storeBoundedContext.core;

import be.kdg.prog6.common.events.AchievementEvent;
import be.kdg.prog6.common.events.AddGameToLibraryEvent;
import be.kdg.prog6.common.events.GameEvent;
import be.kdg.prog6.storeBoundedContext.domain.Achievement;
import be.kdg.prog6.storeBoundedContext.domain.Game;
import be.kdg.prog6.storeBoundedContext.domain.Store;
import be.kdg.prog6.storeBoundedContext.port.in.AddGameToLibraryUseCase;
import be.kdg.prog6.storeBoundedContext.port.in.command.AddGameToLibraryCommand;
import be.kdg.prog6.storeBoundedContext.port.out.AddGameEventPublisher;
import be.kdg.prog6.storeBoundedContext.port.out.GameLoadPort;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AddGameToLibraryUseCaseImp implements AddGameToLibraryUseCase {


    private final GameLoadPort gameLoadPort;
    private final AddGameEventPublisher addGameEventPublisher;

    public AddGameToLibraryUseCaseImp(GameLoadPort gameLoadPort, AddGameEventPublisher addGameEventPublisher) {
        this.gameLoadPort = gameLoadPort;
        this.addGameEventPublisher = addGameEventPublisher;
    }


    @Override
    @Transactional
    public void addGamesToLibrary(AddGameToLibraryCommand command) {

        if (command == null) {
            throw new IllegalArgumentException("Command list cannot be empty");
        }

        UUID playerId = command.playerId();
        String gameName = command.gameName();

        Store store = gameLoadPort.loadAllAvailGames();
        Game game = store.getGameByName(gameName);

        // Transform games into game events
        GameEvent gameEvents = new GameEvent(
                game.getGameId().id(),
                game.getGameName(),
                game.getGameType().toString(),
                game.getAchievementList().stream()
                        .map(achievement -> new AchievementEvent(
                                achievement.getAchievementId().achievementId(),
                                achievement.getAchievementName(),
                                achievement.getAchievementDescription(),
                                achievement.getImageUrl(),
                                false
                        ))
                        .toList(),
                game.getImageUrl(),
                game.getBackgroundImageUrl(),
                game.getDescription(),
                false,
                game.getDomainUrl()
        );


        AddGameToLibraryEvent addGameToLibraryEvent = new AddGameToLibraryEvent(gameEvents, command.playerId());

        addGameEventPublisher.publishAddGameToLibrary(addGameToLibraryEvent);
    }


}
