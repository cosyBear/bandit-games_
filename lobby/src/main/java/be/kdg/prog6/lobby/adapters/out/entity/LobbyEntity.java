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

    @OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RequestAccessEntity> requests = new HashSet<>();

    @Override
    public int hashCode() {
        return java.util.Objects.hash(lobbyId, gameId, lobbyAdmin, guestPlayer, lobbyStatusEntity, createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LobbyEntity that = (LobbyEntity) o;
        return java.util.Objects.equals(lobbyId, that.lobbyId) &&
                java.util.Objects.equals(gameId, that.gameId) &&
                java.util.Objects.equals(lobbyAdmin, that.lobbyAdmin) &&
                java.util.Objects.equals(guestPlayer, that.guestPlayer) &&
                java.util.Objects.equals(lobbyStatusEntity, that.lobbyStatusEntity) &&
                java.util.Objects.equals(createdAt, that.createdAt);
    }


}
