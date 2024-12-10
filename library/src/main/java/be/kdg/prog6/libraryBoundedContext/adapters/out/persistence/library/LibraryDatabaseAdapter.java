package be.kdg.prog6.libraryBoundedContext.adapters.out.persistence.library;

import be.kdg.prog6.common.events.util.DatabaseException;
import be.kdg.prog6.common.events.util.InvalidCategoryException;
import be.kdg.prog6.common.events.util.LibraryNotFoundException;
import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameTypeEntity;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.LibraryEntity;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.port.out.LibrarySavePort;
import be.kdg.prog6.libraryBoundedContext.util.Mapper;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryDatabaseAdapter implements LibraryLoadPort, LibrarySavePort {

    private final LibraryJpaRepository libraryJpaRepository;

    public LibraryDatabaseAdapter(LibraryJpaRepository libraryJpaRepository) {
        this.libraryJpaRepository = libraryJpaRepository;
    }



    @Override
    public Library fetchLibraryWithAllAvailableGames(PlayerId playerId) {
        try {
            LibraryEntity libraryEntity = Optional.ofNullable(
                    libraryJpaRepository.fetchLibraryWithAllAvailableGames(playerId.Id())
            ).orElseThrow(() -> new LibraryNotFoundException("Library not found for Player ID: " + playerId));

            return Mapper.mapDomainLibrary(libraryEntity);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch library with all available games for Player ID: " + playerId, e);
        }
    }

    @Override
    public Library fetchLibraryWithGamesByNamePattern(PlayerId playerId, String gameName) {
        try {
            LibraryEntity libraryEntity = Optional.ofNullable(
                    libraryJpaRepository.fetchLibraryWithGamesByNamePattern(playerId.Id(), gameName)
            ).orElseThrow(() -> new LibraryNotFoundException("No games matching name pattern '" + gameName + "' found for Player ID: " + playerId));

            return Mapper.mapDomainLibrary(libraryEntity);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch games by name pattern '" + gameName + "' for Player ID: " + playerId, e);
        }
    }

    @Override
    public Library fetchLibraryWithGamesByCategory(PlayerId playerId, String category) {
        try {
            LibraryEntity libraryEntity = Optional.ofNullable(
                    libraryJpaRepository.fetchLibraryWithGamesByCategory(playerId.Id(), category)
            ).orElseThrow(() -> new LibraryNotFoundException("No games found in category '" + category + "' for Player ID: " + playerId));

            return Mapper.mapDomainLibrary(libraryEntity);
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("Invalid game category: " + category, e);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch games by category '" + category + "' for Player ID: " + playerId, e);
        }
    }

    @Override
    public Library fetchLibraryWithGameById(UUID gameId) {
        final LibraryEntity libraryEntity = libraryJpaRepository.fetchLibraryWithGameById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Library with game id" + gameId + " not found"));

        return Mapper.mapDomainLibrary(libraryEntity);
    }


    @Override
    public void save(Library library) {
        try {
            LibraryEntity entity = Mapper.mapEntityLibrary(library);
            libraryJpaRepository.save(entity);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to save library for Player ID: " + library.getPlayerId(), e);
        }
    }
}
