package be.kdg.prog6.lobby.adapters.out.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(catalog = "lobby" , name = "lobby")
public class LobbyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID lobbyId;

    private UUID gameId;

    private UUID lobbyAdmin;

    private UUID guestPlayer;
    @Enumerated(EnumType.STRING)
    private LobbyStatusEntity lobbyStatusEntity;
    private LocalDateTime createdAt;

    public LobbyEntity() {

    }


    public LobbyEntity(UUID lobbyId, UUID gameId, UUID lobbyAdmin, UUID guestPlayer, LobbyStatusEntity lobbyStatusEntity, LocalDateTime createdAt) {
        this.lobbyId = lobbyId;
        this.gameId = gameId;
        this.lobbyAdmin = lobbyAdmin;
        this.guestPlayer = guestPlayer;
        this.lobbyStatusEntity = lobbyStatusEntity;
        this.createdAt = createdAt;
    }
}
