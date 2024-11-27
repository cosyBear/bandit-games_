package be.kdg.prog6.lobby.adapters.in;

import be.kdg.prog6.lobby.adapters.dto.CreateLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.JoinLobbyDto;
import be.kdg.prog6.lobby.adapters.dto.LeaveLobbyDto;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.CreateLobbyUseCase;
import be.kdg.prog6.lobby.port.in.JoinLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LeaveLobbyUseCase;
import be.kdg.prog6.lobby.port.in.Query.LobbyCreateQuery;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.command.AddGuestToLobbyCommand;
import be.kdg.prog6.lobby.port.in.command.CreateLobbyCommand;
import be.kdg.prog6.lobby.port.in.command.LeaveLobbyCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lobbies")
public class LobbyController {


    private final CreateLobbyUseCase createLobbyUseCase;
    private final JoinLobbyUseCase joinLobbyUseCase;
    private final LeaveLobbyUseCase leaveLobbyUseCase;


    public LobbyController(CreateLobbyUseCase createLobbyUseCase, JoinLobbyUseCase joinLobbyUseCase, LeaveLobbyUseCase leaveLobbyUseCase) {
        this.createLobbyUseCase = createLobbyUseCase;
        this.joinLobbyUseCase = joinLobbyUseCase;
        this.leaveLobbyUseCase = leaveLobbyUseCase;
    }

    @PostMapping
    public ResponseEntity<LobbyCreateQuery> createLobby(@RequestBody CreateLobbyDto dto) {

        CreateLobbyCommand command = new CreateLobbyCommand(dto.gameID(), dto.lobbyAdminId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createLobbyUseCase.createLobby(command));

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
