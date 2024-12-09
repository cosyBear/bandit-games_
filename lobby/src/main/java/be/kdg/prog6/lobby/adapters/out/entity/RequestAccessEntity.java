package be.kdg.prog6.lobby.adapters.out.entity;

import be.kdg.prog6.lobby.domain.RequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(catalog = "lobby", name = "request_access")
public class RequestAccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID requestId;

    private UUID guestPlayerId;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "lobby_id", nullable = false)
    private LobbyEntity lobby;

    @Override
    public int hashCode() {
        // Exclude the `lobby` field to prevent circular reference
        return java.util.Objects.hash(requestId, guestPlayerId, requestStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestAccessEntity that = (RequestAccessEntity) o;
        return java.util.Objects.equals(requestId, that.requestId) &&
                java.util.Objects.equals(guestPlayerId, that.guestPlayerId) &&
                java.util.Objects.equals(requestStatus, that.requestStatus);
    }

}
