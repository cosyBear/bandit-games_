package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.RemoveFriend;
import be.kdg.prog6.friends.port.out.FriendsPort;
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
    private final FriendsPort friendsPort;

    @Override
    @Transactional
    public void removeFriend(UUID friendId, UUID playerId) {
        final Friends friends = friendsPort.findAll(playerId);

        final Player friend = playerPort.findById(friendId);

        friends.removeFriend(friend);

        friendsPort.deleteFriend(friends, playerId);
    }
}
