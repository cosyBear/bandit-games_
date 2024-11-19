package be.kdg.prog6.libraryBoundedContext.port.in.gameQuery;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

import java.util.UUID;

public record FetchGamesByNameQuery(PlayerId playerId, String gameName) {}
