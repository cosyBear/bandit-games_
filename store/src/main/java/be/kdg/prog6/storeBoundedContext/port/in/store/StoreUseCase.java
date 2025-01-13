package be.kdg.prog6.storeBoundedContext.port.in.store;

import be.kdg.prog6.storeBoundedContext.port.in.command.CreateGameCommand;
import be.kdg.prog6.storeBoundedContext.port.in.command.RemoveGameCommand;

public interface StoreUseCase {


    String addGameToStore(CreateGameCommand gameCommand);
    String removeGameFromStore(RemoveGameCommand gameCommand);

}
