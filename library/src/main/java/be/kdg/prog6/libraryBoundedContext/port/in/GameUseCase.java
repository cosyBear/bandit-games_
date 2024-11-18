package be.kdg.prog6.libraryBoundedContext.port.in;

public interface GameUseCase {

    GameQuery markGameAsFavourite(GameCommand command);

    GameQuery createGame(CreateGameCommand createGameCommand);

    GameQuery givePlayerAnAchievement(EarnAchievementCommand command);

}
