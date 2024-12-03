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
}
