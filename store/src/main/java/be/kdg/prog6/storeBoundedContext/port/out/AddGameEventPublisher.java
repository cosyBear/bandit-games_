package be.kdg.prog6.storeBoundedContext.port.out;

import be.kdg.prog6.common.events.AddGameToLibraryEvent;
import be.kdg.prog6.storeBoundedContext.port.in.command.AddGameToLibraryCommand;

import java.util.List;

public interface AddGameEventPublisher {



    void publishAddGameToLibrary(AddGameToLibraryEvent eventList);
}
