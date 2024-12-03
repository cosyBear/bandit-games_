package be.kdg.prog6.lobby.port.in;

import be.kdg.prog6.lobby.port.in.Query.RequestQuery;
import be.kdg.prog6.lobby.port.in.command.ShowLobbyRequestsQuery;

import java.util.List;

public interface ShowLobbyRequestAccessQueryUseCase {


    List<RequestQuery> showAllLobbyRequests(ShowLobbyRequestsQuery command);
}
