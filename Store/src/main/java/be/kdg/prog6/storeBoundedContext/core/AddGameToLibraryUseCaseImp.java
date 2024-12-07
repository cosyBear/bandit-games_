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
import java.util.List;

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
    public void addGamesToLibrary(List<AddGameToLibraryCommand> commandList) {

        Store store = gameLoadPort.loadAllAvailGames();
        List<Game> gamesList = commandList.stream()
                .map(item -> store.getGameByName(item.gameName()))
                .toList();

            List<GameEvent> gameEvents = gamesList.stream().map(
                item -> {
                    List<AchievementEvent> achievementEvents = item.getAchievementList().stream()
                            .map(achievementEvent -> new AchievementEvent(
                                    achievementEvent.getAchievementId().achievementId(),
                                    achievementEvent.getAchievementName(),
                                    achievementEvent.getAchievementDescription(),
                                    achievementEvent.getImageUrl(),
                                    false
                            ))
                            .toList();

                    return new GameEvent(
                            item.getGameId().id(),
                            item.getGameName(),
                            item.getGameType().toString(),
                            achievementEvents,
                            item.getImageUrl(),
                            item.getBackgroundImageUrl(),
                            item.getDescription(),
                            false
                    );
                }
        ).toList();

        AddGameToLibraryEvent addGameToLibraryEvent = new AddGameToLibraryEvent(gameEvents , commandList.getFirst().playerId());

        addGameEventPublisher.publishAddGameToLibrary(addGameToLibraryEvent);

    }


}
