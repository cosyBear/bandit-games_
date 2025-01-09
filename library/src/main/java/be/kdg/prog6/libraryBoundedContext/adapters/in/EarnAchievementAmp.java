package be.kdg.prog6.libraryBoundedContext.adapters.in;


import be.kdg.prog6.common.events.GameAchievementEvent;
import be.kdg.prog6.common.events.util.AchievementAlreadyEarnedException;
import be.kdg.prog6.common.events.util.AchievementNotFoundException;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.EarnAchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EarnAchievementAmp {


    private final GameUseCase gameUseCase;


    @RabbitListener(queues = "AwardPlayerAchievementQueue")
    public void earnAchievement(GameAchievementEvent gameEvent) {

        log.info("Achievement received for player: {}, game: {}, achievement: {}",
                gameEvent.playerId(), gameEvent.gameName(), gameEvent.AchievementName());

        EarnAchievementCommand achievementCommand = new EarnAchievementCommand(
                new PlayerId(gameEvent.playerId()),
                gameEvent.gameName(),
                gameEvent.AchievementName()
        );

        try {
            gameUseCase.givePlayerAnAchievement(achievementCommand);
            log.info("Achievement successfully granted to player: {}", gameEvent.playerId());
        } catch (AchievementAlreadyEarnedException e) {
            log.warn("Achievement already earned: {}", e.getMessage());
        } catch (AchievementNotFoundException e) {
            log.warn("Achievement not found: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error while granting achievement: {}", e.getMessage(), e);
        }
    }


}
