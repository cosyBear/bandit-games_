package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.out.FriendsPort;
import be.kdg.prog6.friends.port.out.PlayerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor @Slf4j
public class LoadFriendsImpl implements LoadFriends {

    private final PlayerPort playerPort;
    private final FriendsPort friendsPort;
    @Override
    @Transactional(readOnly = true)
    public Friends getAllFriends(UUID playerId) {
        return friendsPort.findAll(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Friends searchForFriend(String nickName) {
        return playerPort.searchForFriend(nickName);
    }
}
