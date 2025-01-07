//package be.kdg.prog6.libraryBoundedContext.adapters.in;
//
//
//import be.kdg.prog6.common.events.GameAchievementEvent;
//import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
//import be.kdg.prog6.libraryBoundedContext.port.in.command.EarnAchievementCommand;
//import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
//import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class EarnAchievementAmp {
//
//
//    private final GameUseCase gameUseCase;
//
//
//    @RabbitListener(queues = "AwardPlayerAchievementQueue")
//    public void earnAchievement(GameAchievementEvent gameEvent) {
//
//        log.info("Achievement recvied ");
//        EarnAchievementCommand achievementCommand = new EarnAchievementCommand(new PlayerId(gameEvent.playerId()),
//                gameEvent.gameName(), gameEvent.gameId(), gameEvent.AchievementName());
//
//        gameUseCase.givePlayerAnAchievement(achievementCommand);
//
//    }
//
//
//}
