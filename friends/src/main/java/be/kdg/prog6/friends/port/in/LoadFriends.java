package be.kdg.prog6.friends.port.in;

import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;

import java.util.List;
import java.util.UUID;

public interface LoadFriends {
    Friends getAllFriends(UUID playerId);
    Friends searchForFriend(String nickName);
}
