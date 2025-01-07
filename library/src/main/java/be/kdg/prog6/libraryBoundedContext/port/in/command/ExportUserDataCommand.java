package be.kdg.prog6.libraryBoundedContext.port.in.command;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

public record ExportUserDataCommand(PlayerId playerId , String playerName) {
}
