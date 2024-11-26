package be.kdg.prog6.friends.adapters.in;

import be.kdg.prog6.friends.adapters.dto.PlayerDto;
import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.in.Query.LoadLobbiesQuery;
import be.kdg.prog6.friends.port.in.Query.LobbyQuery;
import be.kdg.prog6.friends.port.in.RemoveFriend;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import be.kdg.prog6.friends.port.in.lobby.LoadLobbiesUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor @Slf4j
@RestController
@RequestMapping("/friends")
@CrossOrigin(origins = "http://localhost:5173")
public class FriendsRestController {
    private final AddFriend addFriend;
    private final LoadFriends loadFriends;
    private final RemoveFriend removeFriend;
    private final LoadLobbiesUseCase loadLobbiesUseCase;

    @PostMapping
    public ResponseEntity<PlayerDto> addNewFriend(
            @RequestBody AddFriendCommand command
    ) {
        final Player player = addFriend.addFriend(command);

        final PlayerDto response = PlayerDto.convertToDTO(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<PlayerDto>> getAllFriends(
            @PathVariable("playerId") UUID playerId
    ) {
        final Friends friends = loadFriends.getAllFriends(playerId);

        final List<PlayerDto> response = friends.getFriends()
                .stream().map(PlayerDto::convertToDTO).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/nickname")
    public ResponseEntity<List<PlayerDto>> searchByNickname(
            @RequestParam("searchTerm") final String nickname
    ) {
        final Friends friends = loadFriends.searchForFriend(nickname);

        final List<PlayerDto> response = friends.getFriends()
                .stream().map(PlayerDto::convertToDTO).toList();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{playerId}/friends/{friendId}")
    public ResponseEntity<Void> removeFriend(
            @PathVariable("playerId") final UUID playerId,
            @PathVariable("friendId") final UUID friendId
    ) {
        removeFriend.removeFriend(friendId, playerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping
    public ResponseEntity<List<LobbyQuery>> getLobby(LoadLobbiesQuery query) {

        return ResponseEntity.ok().body(loadLobbiesUseCase.fetchFriendsLobbies(query));
    }
}
