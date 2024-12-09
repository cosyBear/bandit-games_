package be.kdg.prog6.storeBoundedContext.adapters.out.persistence;

import be.kdg.prog6.storeBoundedContext.adapters.out.Entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GameJpaRepository extends  JpaRepository<GameEntity, UUID> {


    @Query("SELECT g FROM StoreGameEntity g left join fetch g.achievementList")
    List<GameEntity> getAllGame();


    @Query("SELECT g FROM StoreGameEntity g left join fetch g.achievementList where g.gameId = :gameId")

    GameEntity findGameByGameId(@Param("gameId") UUID gameId);

}