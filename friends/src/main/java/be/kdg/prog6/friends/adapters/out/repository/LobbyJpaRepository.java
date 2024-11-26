package be.kdg.prog6.friends.adapters.out.repository;

import be.kdg.prog6.friends.adapters.out.entity.LobbyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LobbyJpaRepository extends JpaRepository<LobbyJpaEntity, UUID> {


    @Query("select l from LobbyJpaEntity l where l.player.id = :playerId")
    LobbyJpaEntity findAllByPlayerId(@Param("playerId") UUID playerId);

}

// get a list of friends for me
// for each friend get there lobby
// save this list and return it to me
// so i can see all the lobbies for my friends
