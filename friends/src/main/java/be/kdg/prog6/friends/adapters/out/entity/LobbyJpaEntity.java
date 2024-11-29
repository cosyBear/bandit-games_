package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.common.domain.LobbyStatus;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.domain.LobbyId;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lobby", catalog = "friends")
public class LobbyJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerJpaEntity playerJpaEntity;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID gameId;

    @Enumerated(EnumType.STRING)
    private LobbyStatus lobbyStatus;

    public Lobby toDomain() {
        return new Lobby(new LobbyId(this.getId()), this.gameId, this.playerJpaEntity.convertToDomain(), this.lobbyStatus);
    }

    public static LobbyJpaEntity fromDomain(Lobby lobby, PlayerJpaEntity playerJpaEntity, LobbyStatus lobbyStatus) {
        return new LobbyJpaEntity(
                lobby.getId().id(),
                playerJpaEntity,
                lobby.getGameId(),
                lobbyStatus
        );
    }
}
