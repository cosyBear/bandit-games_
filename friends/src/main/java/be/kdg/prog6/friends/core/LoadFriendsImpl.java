package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.out.PlayerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor @Slf4j
public class LoadFriendsImpl implements LoadFriends {

    private final PlayerPort playerPort;
    @Override
    public List<Player> getAllFriends(UUID playerId) {
        return playerPort.findAllFriends(playerId);
    }
}
