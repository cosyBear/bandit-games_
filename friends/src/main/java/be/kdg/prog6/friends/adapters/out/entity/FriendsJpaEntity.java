package be.kdg.prog6.friends.adapters.out.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="friends", catalog = "friends")
public class FriendsJpaEntity {
    @EmbeddedId
    private FriendsId id;
}
