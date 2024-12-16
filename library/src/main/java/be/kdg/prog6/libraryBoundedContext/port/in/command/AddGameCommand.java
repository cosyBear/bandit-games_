package be.kdg.prog6.libraryBoundedContext.port.in.command;

import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

import java.util.List;

public record AddGameCommand(Game game , PlayerId playerId) {
}
