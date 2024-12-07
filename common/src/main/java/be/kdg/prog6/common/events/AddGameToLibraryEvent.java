package be.kdg.prog6.common.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record AddGameToLibraryEvent (List<GameEvent> gameEvent, UUID PlayerId ){
}

