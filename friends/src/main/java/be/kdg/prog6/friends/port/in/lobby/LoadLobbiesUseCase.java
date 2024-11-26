package be.kdg.prog6.friends.port.in.lobby;

import be.kdg.prog6.friends.port.in.Query.LobbyQuery;
import be.kdg.prog6.friends.port.in.Query.LoadLobbiesQuery;

import java.util.List;

public interface LoadLobbiesUseCase {

    List<LobbyQuery> fetchFriendsLobbies(LoadLobbiesQuery query);
}
