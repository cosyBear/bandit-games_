package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.RemoveFriend;
import be.kdg.prog6.friends.port.out.PlayerPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemoveFriendImpl implements RemoveFriend {
    private final PlayerPort playerPort;

    @Override
    @Transactional
    public void removeFriend(UUID friendId, UUID playerId) {
        final Player player = playerPort.findByIdWithFriends(playerId);

        final Player friend = playerPort.findById(friendId);

        player.removeFriend(friend);

        playerPort.savePlayerWithFriends(player);
    }
}
