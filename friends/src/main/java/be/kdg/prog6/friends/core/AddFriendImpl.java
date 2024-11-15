package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import be.kdg.prog6.friends.port.out.PlayerPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor @Slf4j
@Service
public class AddFriendImpl implements AddFriend {
    private final PlayerPort playerPort;
    @Override
    @Transactional
    public Player addFriend(AddFriendCommand command) {
        final Player player = playerPort.findByIdWithFriends(command.playerId());
        final Player friend = playerPort.findById(command.friendId());

        player.addFriend(friend);

        playerPort.saveNewFriend(player, friend.getId().id());

        return player;
    }
}
