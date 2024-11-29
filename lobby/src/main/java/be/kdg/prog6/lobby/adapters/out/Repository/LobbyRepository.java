package be.kdg.prog6.lobby.adapters.out.Repository;


import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LobbyRepository extends JpaRepository<LobbyEntity, UUID> {

    @Query("SELECT l FROM LobbyEntity l WHERE l.lobbyId = :lobbyId")
    LobbyEntity loadByLobbyId(@Param("lobbyId") UUID lobbyId);
}
