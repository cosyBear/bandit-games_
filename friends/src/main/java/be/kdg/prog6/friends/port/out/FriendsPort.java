package be.kdg.prog6.friends.port.out;

import be.kdg.prog6.friends.domain.Friends;

import java.util.List;
import java.util.UUID;

public interface FriendsPort {
    Friends findAll(UUID playerId);
    void saveAllFriends(Friends friends, UUID playerId);
    void deleteFriend(Friends friends, UUID playerId);
}
