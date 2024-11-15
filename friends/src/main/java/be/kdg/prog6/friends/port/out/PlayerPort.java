package be.kdg.prog6.friends.port.out;

import be.kdg.prog6.friends.domain.Player;

import java.util.UUID;

public interface PlayerPort {

    Player findById(UUID uuid);

    void saveNewFriend(Player player, UUID newFriendId);

    Player findByIdWithFriends(UUID uuid);
}
