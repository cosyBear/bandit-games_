package be.kdg.prog6.storeBoundedContext.port.in.command;

import java.util.UUID;

public record RemoveGameCommand(UUID gameId) {
}
