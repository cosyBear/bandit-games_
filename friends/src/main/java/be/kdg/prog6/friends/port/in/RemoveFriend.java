package be.kdg.prog6.friends.port.in;

import java.util.UUID;

public interface RemoveFriend {
    void removeFriend(UUID friendId, UUID playerId);
}
