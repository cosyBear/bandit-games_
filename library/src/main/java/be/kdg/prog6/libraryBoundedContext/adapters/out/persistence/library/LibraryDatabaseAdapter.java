package be.kdg.prog6.libraryBoundedContext.adapters.out.persistence.library;

import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameTypeEntity;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.LibraryEntity;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.port.out.LibrarySavePort;
import be.kdg.prog6.libraryBoundedContext.util.GameMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LibraryDatabaseAdapter implements LibraryLoadPort, LibrarySavePort {


    private final LibraryJpaRepository libraryJpaRepository;

    public LibraryDatabaseAdapter(LibraryJpaRepository libraryJpaRepository) {
        this.libraryJpaRepository = libraryJpaRepository;
    }


    @Override
    public Library fetchLibraryWithAllAvailableGames(PlayerId playerId) {
        return GameMapper.mapDomainLibrary(libraryJpaRepository.fetchLibraryWithAllAvailableGames(playerId.Id()));
    }

    @Override
    public Library fetchLibraryWithGamesByNamePattern(PlayerId playerId, String gameName) {

        return GameMapper.mapDomainLibrary(libraryJpaRepository.fetchLibraryWithGamesByNamePattern(playerId.Id(), gameName));
    }

    @Override
    public Library fetchLibraryWithGamesByCategory(PlayerId playerId, GameType category) {
        return GameMapper.mapDomainLibrary(libraryJpaRepository.fetchLibraryWithGamesByCategory(playerId.Id(), GameTypeEntity.valueOf(category.toString())));
    }

    @Override
    public void save(Library library) {
        LibraryEntity entity = GameMapper.mapEntityLibrary(library);
        libraryJpaRepository.save(entity);
    }
}
