package be.kdg.prog6.lobby.adapters.out.Repository;


import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LobbyRepository extends JpaRepository<LobbyEntity, UUID> {

    LobbyEntity loadByLobbyId(UUID id);
}
