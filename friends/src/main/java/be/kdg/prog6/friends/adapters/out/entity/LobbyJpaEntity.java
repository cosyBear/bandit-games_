package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.domain.LobbyId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lobby", catalog = "friends")
public class LobbyJpaEntity {


    @Id
    private UUID id;
    private UUID playerId;
    private UUID gameId;

    public Lobby toDomain() {
        return new Lobby(new LobbyId(this.getId()), this.gameId, this.playerId);
    }

}
