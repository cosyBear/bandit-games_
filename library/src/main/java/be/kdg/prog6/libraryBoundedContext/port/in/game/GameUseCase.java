package be.kdg.prog6.libraryBoundedContext.port.in.game;

import be.kdg.prog6.libraryBoundedContext.port.in.command.AddGameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.EarnAchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.command.PlayerGameOwnershipCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;

import java.util.List;
import java.util.Map;

public interface GameUseCase {

    GameQuery toggleGameFavourite(GameCommand command);

    GameQuery givePlayerAnAchievement(EarnAchievementCommand command);

    Map<Boolean, String> hasPlayerPurchasedGame(List<PlayerGameOwnershipCommand> command);

    void addGameToPlayerLibrary(AddGameCommand command);

}
