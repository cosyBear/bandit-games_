package be.kdg.prog6.friends.port.in;

import be.kdg.prog6.friends.domain.Player;

import java.util.List;
import java.util.UUID;

public interface LoadFriends {
    List<Player> getAllFriends(UUID playerId);
}
