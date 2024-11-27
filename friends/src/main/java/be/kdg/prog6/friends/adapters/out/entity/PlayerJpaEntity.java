package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.friends.domain.Gender;
import be.kdg.prog6.friends.domain.Player;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Entity @Table(name = "player", catalog = "friends")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PlayerJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String gender;
    @OneToOne(mappedBy = "playerJpaEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private AddressJpaEntity addressJpaEntity;

    @OneToOne(mappedBy = "playerJpaEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private LobbyJpaEntity lobbyJpaEntity;

    public PlayerJpaEntity(UUID id, String nickname, String firstName, String lastName, String gender) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Player convertToDomain() {
        return new Player(
                new Player.PlayerId(this.id),
                this.nickname,
                this.firstName,
                this.lastName,
                Gender.valueOf(this.gender)
        );
    }
}
