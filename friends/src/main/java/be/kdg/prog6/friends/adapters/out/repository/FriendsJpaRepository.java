package be.kdg.prog6.friends.adapters.out.repository;

import be.kdg.prog6.friends.adapters.out.entity.FriendsJpaEntity;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FriendsJpaRepository extends JpaRepository<FriendsJpaEntity, UUID> {
    @Query("""
            SELECT f from FriendsJpaEntity f
            WHERE f.id.playerId = :playerId
            """)
    List<FriendsJpaEntity> findAllByPlayerId(@Param("playerId") UUID playerId);

    @Query("""
            SELECT f from FriendsJpaEntity f
            WHERE f.id.playerId = :playerId
            AND f.id.friendId = :friendId
            """)
    Optional<FriendsJpaEntity> findByPlayerAndFriendId(@Param("playerId") UUID playerId, @Param("friendId") UUID friendId);
}
