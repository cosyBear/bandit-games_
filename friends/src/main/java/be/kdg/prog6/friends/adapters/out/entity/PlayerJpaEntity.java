package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.friends.domain.Gender;
import be.kdg.prog6.friends.domain.Player;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity @Table(catalog = "friends", name = "player")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PlayerJpaEntity {
    @Id
    private UUID id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String gender;
    @OneToOne(mappedBy = "playerJpaEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private AddressJpaEntity addressJpaEntity;

    @ManyToMany
    @JoinTable(
            name = "player_friends",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<PlayerJpaEntity> friends = new HashSet<>();

    public PlayerJpaEntity(UUID id, String nickname, String firstName, String lastName, String gender, AddressJpaEntity addressJpaEntity) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.addressJpaEntity = addressJpaEntity;
    }

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
                Gender.valueOf(this.gender),
                this.addressJpaEntity.convertToDomain()
        );
    }

    public Player convertToDomainWithFriends() {
        return new Player(
                new Player.PlayerId(this.id),
                this.nickname,
                this.firstName,
                this.lastName,
                Gender.valueOf(this.gender),
                this.addressJpaEntity.convertToDomain(),
                this.friends.stream().map(PlayerJpaEntity::convertToDomain).collect(Collectors.toSet())
        );
    }

    public void addFriend(PlayerJpaEntity newFriendJpaEntity) {
        this.friends.add(newFriendJpaEntity);
    }
}
