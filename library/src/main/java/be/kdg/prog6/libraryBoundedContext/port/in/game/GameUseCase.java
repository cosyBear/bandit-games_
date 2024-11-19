package be.kdg.prog6.libraryBoundedContext.port.in.game;

import be.kdg.prog6.libraryBoundedContext.port.in.EarnAchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;

public interface GameUseCase {

    GameQuery markGameAsFavourite(GameCommand command);

    GameQuery givePlayerAnAchievement(EarnAchievementCommand command);

}
