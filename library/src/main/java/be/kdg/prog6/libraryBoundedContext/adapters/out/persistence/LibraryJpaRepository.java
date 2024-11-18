package be.kdg.prog6.libraryBoundedContext.adapters.out.persistence;

import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.LibraryEntity;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameEntity;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LibraryJpaRepository  extends JpaRepository<Library, Long> {


    //get games  with name
    //get games by cat

    @Query("SELECT l FROM LibraryEntity l LEFT JOIN FETCH l.games WHERE l.player.id = :playerId")
    LibraryEntity fetchLibraryWithAllAvailableGames(@Param("playerId") UUID playerId);

    @Query("SELECT l FROM LibraryEntity l LEFT JOIN FETCH l.games g " +
            "WHERE l.player.id = :playerId AND g.gameName LIKE :gameName%")
    LibraryEntity fetchLibraryWithGamesByNamePattern(@Param("playerId") UUID playerId, @Param("gameName") String gameName);

    @Query("SELECT g FROM GameEntity g WHERE LOWER(g.gameName) LIKE LOWER(CONCAT('%', :gameName, '%'))")
    List<GameEntity> searchGamesByName(@Param("gameName") String gameName);



}
