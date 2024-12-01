package be.kdg.prog6.libraryBoundedContext.port.out;

import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

import java.util.UUID;

public interface LibraryLoadPort {


    Library fetchLibraryWithAllAvailableGames(PlayerId playerId);

    Library fetchLibraryWithGamesByNamePattern(PlayerId playerId, String gameName);

    Library fetchLibraryWithGamesByCategory(PlayerId playerId, String category);
}
