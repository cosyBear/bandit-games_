package be.kdg.prog6.lobby.adapters.in;

import be.kdg.prog6.lobby.adapters.dto.CreateLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.JoinLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.LeaveLobbyDto;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.CreateLobbyUseCase;
import be.kdg.prog6.lobby.port.in.JoinLobbyUseCase;
import be.kdg.prog6.lobby.port.in.LoadLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LeaveLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LobbyCreateQuery;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.command.AddGuestToLobbyCommand;
import be.kdg.prog6.lobby.port.in.command.CreateLobbyCommand;
import be.kdg.prog6.lobby.port.in.command.LeaveLobbyCommand;
import be.kdg.prog6.lobby.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/lobbies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LobbyController {


    private final CreateLobbyUseCase createLobbyUseCase;
    private final JoinLobbyUseCase joinLobbyUseCase;
    private final LeaveLobbyUseCase leaveLobbyUseCase;
    private final LoadLobbyUseCase loadLobbyUseCase;

    @PostMapping
    public ResponseEntity<LobbyCreateQuery> createLobby(@RequestBody CreateLobbyDto dto) {

        CreateLobbyCommand command = new CreateLobbyCommand(dto.gameID(), dto.lobbyAdminId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createLobbyUseCase.createLobby(command));

    }

    @GetMapping("/{lobbyId}")
    public ResponseEntity<LobbyUpdateQuery> getLobbyDetails(@PathVariable("lobbyId") UUID lobbyId) {
        final Lobby lobby = loadLobbyUseCase.findLobbyById(lobbyId);

        final LobbyUpdateQuery updateQuery = Mapper.mapToUpdateQuery(lobby);

        return ResponseEntity.status(HttpStatus.OK).body(updateQuery);
    }


    @PatchMapping("/joinLobby")
    public ResponseEntity<LobbyUpdateQuery> addGuestPlayerTOLobby(@RequestBody JoinLobbyDto dto) {

        AddGuestToLobbyCommand command = new AddGuestToLobbyCommand(new LobbyId(dto.lobbyId()), dto.guestId());

        return ResponseEntity.status(HttpStatus.OK).body(joinLobbyUseCase.addGuestToLobby(command));
    }


    @PatchMapping
    public ResponseEntity<LobbyUpdateQuery> leaveLobby(@RequestBody LeaveLobbyDto dto) {

        LeaveLobbyCommand command = new LeaveLobbyCommand(new LobbyId(dto.lobbyId()), dto.guestPlayerId());

        return ResponseEntity.status(HttpStatus.OK).body(leaveLobbyUseCase.leaveLobby(command));

    }


}
