package be.kdg.prog6.friends.core;


import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.in.Query.LobbyQuery;
import be.kdg.prog6.friends.port.in.Query.LoadLobbiesQuery;
import be.kdg.prog6.friends.port.in.lobby.LoadLobbiesUseCase;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadFriendsLobbies implements LoadLobbiesUseCase {

    private final LoadFriends loadFriends;
    private final LobbyLoadPort lobbyLoadPort;

    @Override
    @Transactional(readOnly = true)
    public List<LobbyQuery> fetchFriendsLobbies(LoadLobbiesQuery query) {

        Friends friends = loadFriends.getAllFriends(query.playerId());
        log.info(String.valueOf(friends.getFriends().size()));

        List<Lobby> lobbies = friends.getFriends().stream().map(
                lobby -> lobbyLoadPort.loadLobbies(lobby.getId().id())

        ).toList();

        return lobbies.stream().map(
                lobby -> new LobbyQuery(lobby.getId().id(), lobby.getGameId(), lobby.getPlayer().getId().id())
        ).toList();
    }
}
