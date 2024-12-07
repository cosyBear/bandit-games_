package be.kdg.prog6.storeBoundedContext.port.in.command;

import java.util.UUID;

public record AddGameToLibraryCommand (UUID playerId, String gameName){
}
