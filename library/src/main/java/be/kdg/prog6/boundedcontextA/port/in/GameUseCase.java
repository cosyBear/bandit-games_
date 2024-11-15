package be.kdg.prog6.boundedcontextA.port.in;

public interface GameUseCase {

    GameQuery markGameAsFavourite(GameCommand command);

    GameQuery createGame(CreateGameCommand createGameCommand);
}
