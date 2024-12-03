package be.kdg.prog6.lobby.core;

import be.kdg.prog6.lobby.domain.RequestAccess;
import be.kdg.prog6.lobby.port.in.Query.RequestQuery;
import be.kdg.prog6.lobby.port.in.ShowLobbyRequestAccessQueryUseCase;
import be.kdg.prog6.lobby.port.in.command.ShowLobbyRequestsQuery;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.util.Mapper;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Slf4j
@Service
public class ShowLobbyRequestAccessQueryUseCaseImp implements ShowLobbyRequestAccessQueryUseCase {


    private final LobbyLoadPort lobbyLoadPort;


    @Override
    @Transactional
    public List<RequestQuery> showAllLobbyRequests(ShowLobbyRequestsQuery command) {
        List<RequestAccess> queryList = lobbyLoadPort.loadRequestAccessForLobby(command.lobbyId());

        if (queryList.isEmpty()) {
            log.info("No requests found for lobby {}", command.lobbyId());
            return List.of();
        }

        List<RequestQuery> requestQueryList = queryList.stream()
                .map(requestAccess -> Mapper.RequestToQuery(requestAccess, command.lobbyId()))
                .toList();

        log.info("All the requests for lobby {}: {}", command.lobbyId(), requestQueryList);
        return requestQueryList;
    }

}
