package be.kdg.prog6.friends.core;


import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.in.Query.LobbyQuery;
import be.kdg.prog6.friends.port.in.Query.LoadLobbiesQuery;
import be.kdg.prog6.friends.port.in.lobby.LoadLobbiesUseCase;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadFriendsLobbies implements LoadLobbiesUseCase {

    private final LoadFriends loadFriends;
    private final LobbyLoadPort lobbyLoadPort;

    public LoadFriendsLobbies(LoadFriends loadFriends, LobbyLoadPort lobbyLoadPort) {
        this.loadFriends = loadFriends;
        this.lobbyLoadPort = lobbyLoadPort;
    }


    @Override
    public List<LobbyQuery> fetchFriendsLobbies(LoadLobbiesQuery query) {

        Friends friends  = loadFriends.getAllFriends(query.playerId());

       List<Lobby> lobbies =  friends.getFriends().stream().map(
               lobby -> lobbyLoadPort.loadLobbies(lobby.getId().id())

        ).toList();

        return lobbies.stream().map(
                lobby -> new LobbyQuery(lobby.getLobbyId().id(),lobby.getGameId(),lobby.getPlayerId())
        ).toList();
    }
}
