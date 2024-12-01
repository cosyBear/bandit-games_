package be.kdg.prog6.libraryBoundedContext.adapters.out.persistence.library;

import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameTypeEntity;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.LibraryEntity;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LibraryJpaRepository  extends JpaRepository<LibraryEntity, UUID> {


    @Query("SELECT l FROM LibraryEntity l LEFT JOIN FETCH l.games WHERE l.playerEntity.playerId = :playerId")
    LibraryEntity fetchLibraryWithAllAvailableGames(@Param("playerId") UUID playerId);


    @Query("SELECT l FROM LibraryEntity l LEFT JOIN FETCH l.games g " +
            "WHERE l.playerEntity.playerId = :playerId " +
            "AND LOWER(g.gameName) LIKE CONCAT('%', LOWER(:gameName), '%')")
    LibraryEntity fetchLibraryWithGamesByNamePattern(@Param("playerId") UUID playerId, @Param("gameName") String gameName);


    @Query("SELECT l FROM LibraryEntity l LEFT JOIN FETCH l.games g " +
            "WHERE l.playerEntity.playerId = :playerId AND LOWER(g.gameType) LIKE CONCAT('%', LOWER(:gameType), '%')")
    LibraryEntity fetchLibraryWithGamesByCategory(@Param("playerId") UUID playerId, @Param("gameType") String gameType);



}
