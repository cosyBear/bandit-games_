package be.kdg.prog6.storeBoundedContext.core;

import be.kdg.prog6.storeBoundedContext.domain.*;
import be.kdg.prog6.storeBoundedContext.domain.id.AchievementId;
import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import be.kdg.prog6.storeBoundedContext.port.in.command.CreateGameCommand;
import be.kdg.prog6.storeBoundedContext.port.in.command.RemoveGameCommand;
import be.kdg.prog6.storeBoundedContext.port.in.store.StoreUseCase;
import be.kdg.prog6.storeBoundedContext.port.out.GameLoadPort;
import be.kdg.prog6.storeBoundedContext.port.out.StoreSavePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreUseCaseImp implements StoreUseCase {


    private final GameLoadPort gameLoadPort;
    private final StoreSavePort storeSavePort;


    @Override
    @Transactional
    public String addGameToStore(CreateGameCommand command) {
        Store store = gameLoadPort.loadAllAvailGames();


        SystemRequirements systemRequirements = new SystemRequirements(
                command.systemRequirementsCommand().minimumOperatingSystem(),
                command.systemRequirementsCommand().minimumProcessor(),
                command.systemRequirementsCommand().minimumMemoryInGB(),
                command.systemRequirementsCommand().minimumGraphicsCard(),
                command.systemRequirementsCommand().minimumStorageInGB(),
                command.systemRequirementsCommand().minimumDirectXVersion(),
                command.systemRequirementsCommand().recommendedOperatingSystem(),
                command.systemRequirementsCommand().recommendedProcessor(),
                command.systemRequirementsCommand().recommendedMemoryInGB(),
                command.systemRequirementsCommand().recommendedGraphicsCard(),
                command.systemRequirementsCommand().recommendedStorageInGB(),
                command.systemRequirementsCommand().recommendedDirectXVersion()
        );

        Game game = new Game(
                new GameId(UUID.randomUUID()),
                command.gameName(),
                GameType.valueOf(command.gameType()),
                command.imageUrl(),
                command.backgroundImageUrl(),
                command.description(),
                command.price(),
                0.0,
                systemRequirements
        );

        List<Achievement> achievements = command.achievements().stream()
                .map(achievementCommand -> new Achievement(
                        new AchievementId(UUID.randomUUID()),
                        achievementCommand.achievementName(),
                        achievementCommand.achievementDescription(),
                        achievementCommand.imageUrl()
                ))
                .toList();

        game.setAchievementList(achievements);

        store.addGame(game);

        storeSavePort.save(store);
        log.info("The game is added to the store...");
        return "The game is added to the store";
    }

    @Override
    @Transactional
    public String removeGameFromStore(RemoveGameCommand command) {
        Store store = gameLoadPort.loadAllAvailGames();
        store.removeGame(command.gameId());
        log.info("game is removed ");
        storeSavePort.save(store);
        return "the game is removed from the store";
    }
}
