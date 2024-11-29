package be.kdg.prog6.friends.adapters.out.repository;

import be.kdg.prog6.friends.adapters.out.entity.LobbyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LobbyJpaRepository extends JpaRepository<LobbyJpaEntity, UUID> {


    @Query("select l from LobbyJpaEntity l where l.playerJpaEntity.id = :playerId")
    Optional<LobbyJpaEntity> findAllByPlayerId(@Param("playerId") UUID playerId);

}
