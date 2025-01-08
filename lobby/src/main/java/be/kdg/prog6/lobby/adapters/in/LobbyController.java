package be.kdg.prog6.lobby.adapters.in;

import be.kdg.prog6.lobby.adapters.dto.CreateLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.LeaveLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.CreateRequestAccessDto;
import be.kdg.prog6.lobby.adapters.dto.RequestAccessDto;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.RequestStatus;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.*;
import be.kdg.prog6.lobby.port.in.Query.*;
import be.kdg.prog6.lobby.port.in.command.*;
import be.kdg.prog6.lobby.port.out.LobbySsePort;
import be.kdg.prog6.lobby.port.out.StartGameSsePort;
import be.kdg.prog6.lobby.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/lobbies")
@RequiredArgsConstructor
public class LobbyController {

    private final StartGameSsePort startGameSsePort;

    private static final Logger log = LoggerFactory.getLogger(LobbyController.class);
    private final CreateLobbyUseCase createLobbyUseCase;
    private final JoinLobbyUseCase joinLobbyUseCase;
    private final LeaveLobbyUseCase leaveLobbyUseCase;
    private final CreateRequestAccessUseCase createRequestAccessUseCase;
    private final ShowLobbyRequestAccessQueryUseCase showLobbyRequestAccessQueryUseCase;
    private final LoadLobbyUseCase loadLobbyUseCase;
    private final StartGame startGame;


    private final LobbySsePort lobbySsePort;


    @PostMapping
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<LobbyCreateQuery> createLobby(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateLobbyDto dto) {

        String userId = jwt.getClaimAsString("UserId");

        UUID lobbyAdminId = UUID.fromString(userId);

        CreateLobbyCommand command = new CreateLobbyCommand(dto.gameID(), lobbyAdminId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createLobbyUseCase.createLobby(command));

    }

    @GetMapping("/{lobbyId}")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<LobbyUpdateQuery> getLobbyDetails(@PathVariable("lobbyId") UUID lobbyId) {
        final Lobby lobby = loadLobbyUseCase.findLobbyById(lobbyId);

        final LobbyUpdateQuery updateQuery = Mapper.mapToUpdateQuery(lobby);

        return ResponseEntity.status(HttpStatus.OK).body(updateQuery);
    }


    @PatchMapping
    @PreAuthorize("hasAuthority('LobbyManagement')")

    public ResponseEntity<LobbyUpdateQuery> leaveLobby(@RequestBody LeaveLobbyDto dto) {

        LeaveLobbyCommand command = new LeaveLobbyCommand(new LobbyId(dto.lobbyId()), dto.guestPlayerId());

        return ResponseEntity.status(HttpStatus.OK).body(leaveLobbyUseCase.leaveLobby(command));

    }


    @PostMapping("/{lobbyId}/requests")
    @PreAuthorize("hasAuthority('LobbyManagement')")

    public ResponseEntity<String> createRequestAccess(
            @PathVariable("lobbyId") UUID lobbyId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);

        CreateRequestAccessCommand createRequestAccessCommand = new CreateRequestAccessCommand(new LobbyId(lobbyId), playerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createRequestAccessUseCase.createRequest(createRequestAccessCommand));
    }


    @GetMapping("/{lobbyId}/request")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<List<RequestQuery>> showAllRequestForLobby(@PathVariable("lobbyId") UUID lobbyId) {

        return ResponseEntity.status(HttpStatus.OK).body(showLobbyRequestAccessQueryUseCase.
                showAllLobbyRequests(new ShowLobbyRequestsQuery(new LobbyId(lobbyId))));
    }


    @PatchMapping("/joinLobby")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public ResponseEntity<String> addGuestPlayerTOLobby(@RequestBody RequestAccessDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(joinLobbyUseCase.requestAccessToJoinLobby(
                new RequestAccessCommand(new LobbyId(dto.lobbyId()), dto.guestId(), RequestStatus.valueOf(dto.status()))));
    }

    @GetMapping("/events")
    public SseEmitter subscribeToEvents() {
        return lobbySsePort.createEmitter();
    }


    @PostMapping("/{lobbyId}/start")
    @PreAuthorize("hasAnyAuthority('GameAndEvents')")
    public ResponseEntity<StartGameQuery> startGame(@PathVariable("lobbyId") UUID lobbyId) {
        return ResponseEntity.status(HttpStatus.OK).body(startGame.startGame(new StartGameCommand(lobbyId)));
    }

    @GetMapping("/sse/start-game")
    @PreAuthorize("hasAuthority('LobbyManagement')")
    public SseEmitter streamStartGameEvents() {
        return startGameSsePort.createEmitter();
    }

}
