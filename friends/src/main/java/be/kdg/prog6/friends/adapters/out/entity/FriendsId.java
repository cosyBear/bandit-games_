package be.kdg.prog6.friends.adapters.out.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsId {
    private UUID playerId;
    private UUID friendId;
}
