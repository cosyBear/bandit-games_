package be.kdg.prog6.lobby.adapters.in;

import be.kdg.prog6.lobby.adapters.dto.CreateLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.LeaveLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.CreateRequestAccessDto;
import be.kdg.prog6.lobby.adapters.dto.RequestAccessDto;
import be.kdg.prog6.lobby.domain.RequestStatus;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.CreateLobbyUseCase;
import be.kdg.prog6.lobby.port.in.CreateRequestAccessUseCase;
import be.kdg.prog6.lobby.port.in.JoinLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LeaveLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LobbyCreateQuery;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.Query.RequestQuery;
import be.kdg.prog6.lobby.port.in.ShowLobbyRequestAccessQueryUseCase;
import be.kdg.prog6.lobby.port.in.command.*;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/lobbies")
public class LobbyController {


    private final CreateLobbyUseCase createLobbyUseCase;
    private final JoinLobbyUseCase joinLobbyUseCase;
    private final LeaveLobbyUseCase leaveLobbyUseCase;
    private final CreateRequestAccessUseCase createRequestAccessUseCase;
    private final ShowLobbyRequestAccessQueryUseCase  showLobbyRequestAccessQueryUseCase;


    public LobbyController(CreateLobbyUseCase createLobbyUseCase, JoinLobbyUseCase joinLobbyUseCase, LeaveLobbyUseCase leaveLobbyUseCase, CreateRequestAccessUseCase createRequestAccessUseCase, ShowLobbyRequestAccessQueryUseCase showLobbyRequestAccessQueryUseCase) {
        this.createLobbyUseCase = createLobbyUseCase;
        this.joinLobbyUseCase = joinLobbyUseCase;
        this.leaveLobbyUseCase = leaveLobbyUseCase;
        this.createRequestAccessUseCase = createRequestAccessUseCase;
        this.showLobbyRequestAccessQueryUseCase = showLobbyRequestAccessQueryUseCase;
    }

    @PostMapping
    public ResponseEntity<LobbyCreateQuery> createLobby(@RequestBody CreateLobbyDto dto) {

        CreateLobbyCommand command = new CreateLobbyCommand(dto.gameID(), dto.lobbyAdminId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createLobbyUseCase.createLobby(command));

    }



    @PatchMapping
    public ResponseEntity<LobbyUpdateQuery> leaveLobby(@RequestBody LeaveLobbyDto dto) {

        LeaveLobbyCommand command = new LeaveLobbyCommand(new LobbyId(dto.lobbyId()), dto.guestPlayerId());

        return ResponseEntity.status(HttpStatus.OK).body(leaveLobbyUseCase.leaveLobby(command));

    }


    @PostMapping("/{lobbyId}/requests")
    public ResponseEntity<String> createRequestAccess(
            @PathVariable("lobbyId") UUID lobbyId,
            @RequestBody CreateRequestAccessDto dto
    ) {

        CreateRequestAccessCommand createRequestAccessCommand = new CreateRequestAccessCommand(new LobbyId(lobbyId), dto.guestId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createRequestAccessUseCase.createRequest(createRequestAccessCommand));
    }


    @GetMapping("/{lobbyId}/request")
    public ResponseEntity<List<RequestQuery>> showAllRequestForLobby(@PathVariable("lobbyId") UUID lobbyId){

        return ResponseEntity.status(HttpStatus.OK).body(showLobbyRequestAccessQueryUseCase.
                showAllLobbyRequests(new ShowLobbyRequestsQuery(new LobbyId(lobbyId))));


    }


    @PatchMapping("/joinLobby")
    public ResponseEntity<String> addGuestPlayerTOLobby(@RequestBody RequestAccessDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(joinLobbyUseCase.requestAccessToJoinLobby(
                new RequestAccessCommand(new LobbyId(dto.LobbyId()) , dto.guestId(), RequestStatus.valueOf(dto.status()))));
    }






}
