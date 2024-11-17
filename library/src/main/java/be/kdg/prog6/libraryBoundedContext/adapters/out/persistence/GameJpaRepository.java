package be.kdg.prog6.libraryBoundedContext.adapters.out.persistence;

import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameEntity;
import be.kdg.prog6.libraryBoundedContext.adapters.out.Entity.GameTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameJpaRepository extends JpaRepository<GameEntity , UUID> {



    @Query("SELECT g FROM GameEntity g WHERE g.favourite = true")
    List<GameEntity> fetchFavouriteGames();

    @Query("SELECT g FROM GameEntity g WHERE LOWER(g.gameName) = LOWER(:gameName)")
    Optional<GameEntity> findByGameName(@Param("gameName") String gameName);

    @Query("select g from GameEntity g where g.gameType = :Category")
    List<GameEntity> getGamesByCategory(@Param("Category")GameTypeEntity Category);
}
