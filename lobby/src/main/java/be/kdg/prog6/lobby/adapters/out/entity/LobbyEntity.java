package be.kdg.prog6.lobby.adapters.out.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(catalog = "lobby", name = "lobby")
@AllArgsConstructor
@NoArgsConstructor
public class LobbyEntity {

    @Id
    private UUID lobbyId;

    private UUID gameId;

    private UUID lobbyAdmin;

    private UUID guestPlayer;
    @Enumerated(EnumType.STRING)
    private LobbyStatusEntity lobbyStatusEntity;
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(
            joinColumns = @JoinColumn(name = "lobby_id")
    )
    private Set<RequestAccessEntity> requests = new HashSet<>();




}
