package be.kdg.prog6.libraryBoundedContext.port.in.game;

import be.kdg.prog6.libraryBoundedContext.port.in.command.AddGameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.EarnAchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.PlayerGameOwnershipCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;

public interface GameUseCase {

    GameQuery toggleGameFavourite(GameCommand command);

    GameQuery givePlayerAnAchievement(EarnAchievementCommand command);

    Boolean hasPlayerPurchasedGame(PlayerGameOwnershipCommand command);

    void addGameToPlayerLibrary(AddGameCommand command);

}
