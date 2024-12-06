package be.kdg.prog6.lobby.adapters.out.Repository;


import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import be.kdg.prog6.lobby.adapters.out.entity.RequestAccessEntity;
import be.kdg.prog6.lobby.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LobbyRepository extends JpaRepository<LobbyEntity, UUID> {

    @Query("SELECT l FROM LobbyEntity l LEFT JOIN FETCH l.requests WHERE l.lobbyId = :lobbyId")
    LobbyEntity loadByLobbyId(@Param("lobbyId") UUID lobbyId);


    @Query("SELECT r FROM LobbyEntity l JOIN l.requests r WHERE l.lobbyId = :lobbyId AND r.requestStatus = 'CREATED'")
    List<RequestAccessEntity> loadCreatedRequests(@Param("lobbyId") UUID lobbyId);
}
