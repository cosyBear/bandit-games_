package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import be.kdg.prog6.friends.port.out.FriendsPort;
import be.kdg.prog6.friends.port.out.PlayerPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor @Slf4j
@Service
public class AddFriendImpl implements AddFriend {
    private final PlayerPort playerPort;
    private final FriendsPort friendsPort;

    @Override
    @Transactional
    public Player addFriend(AddFriendCommand command) {
        final Friends friends = friendsPort.findAll(command.playerId());
        final Player friend = playerPort.findById(command.friendId());

        friends.addFriend(friend);

        friendsPort.saveAllFriends(friends, command.playerId());

        return friend;
    }
}
