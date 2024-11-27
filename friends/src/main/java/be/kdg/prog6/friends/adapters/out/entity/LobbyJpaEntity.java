package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.domain.LobbyId;
import be.kdg.prog6.friends.domain.Player;
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

    public Lobby toDomain() {
        return new Lobby(new LobbyId(this.getId()), this.gameId, this.playerJpaEntity.convertToDomain());
    }

    public static LobbyJpaEntity fromDomain(Lobby lobby, PlayerJpaEntity playerJpaEntity) {
        return new LobbyJpaEntity(
                lobby.getId().id(),
                playerJpaEntity,
                lobby.getGameId()
        );
    }
}
