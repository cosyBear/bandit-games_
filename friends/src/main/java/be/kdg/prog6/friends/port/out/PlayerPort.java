package be.kdg.prog6.friends.port.out;

import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerPort {

    Player findById(UUID uuid);

//    Player findByIdWithFriends(UUID uuid);
//
//    List<Friends> findAllFriends(UUID playerId);

    Friends searchForFriend(String nickName);

//    void savePlayerWithFriends(Player player);
}
