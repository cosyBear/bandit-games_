package be.kdg.prog6.friends.adapters.out.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsId {
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID playerId;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID friendId;
}
