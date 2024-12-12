package be.kdg.prog6.storeBoundedContext.port.in;

import be.kdg.prog6.storeBoundedContext.port.in.command.AddGameToLibraryCommand;

import java.util.List;

public interface AddGameToLibraryUseCase {


    void addGamesToLibrary(List<AddGameToLibraryCommand> commandList);




}
