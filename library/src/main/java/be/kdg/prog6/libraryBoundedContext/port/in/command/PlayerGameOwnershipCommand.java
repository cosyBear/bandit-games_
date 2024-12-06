package be.kdg.prog6.libraryBoundedContext.port.in.command;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

import java.util.UUID;

public record PlayerGameOwnershipCommand (PlayerId playerId , String gameName){
}
