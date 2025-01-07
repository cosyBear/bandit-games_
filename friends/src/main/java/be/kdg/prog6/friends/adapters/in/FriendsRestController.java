package be.kdg.prog6.friends.adapters.in;

import be.kdg.prog6.friends.adapters.dto.AddFriendDto;
import be.kdg.prog6.friends.adapters.dto.PlayerDto;
import be.kdg.prog6.friends.core.LoadFriendsLobbies;
import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.in.Query.LoadLobbiesQuery;
import be.kdg.prog6.friends.port.in.Query.LobbyQuery;
import be.kdg.prog6.friends.port.in.RemoveFriend;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendsRestController {
    private final AddFriend addFriend;
    private final LoadFriends loadFriends;
    private final RemoveFriend removeFriend;
    private final LoadFriendsLobbies loadFriendsLobbies;

    @PostMapping
    @PreAuthorize("hasAuthority('FriendsManagement')")
    public ResponseEntity<PlayerDto> addNewFriend(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody AddFriendDto dto) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);

        final Player player = addFriend.addFriend(new AddFriendCommand(playerId, dto.friendId()));

        final PlayerDto response = PlayerDto.convertToDTO(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('FriendsManagement')")
    public ResponseEntity<List<PlayerDto>> getAllFriends(
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);

        final Friends friends = loadFriends.getAllFriends(playerId);

        final List<PlayerDto> response = friends.getFriends()
                .stream().map(PlayerDto::convertToDTO).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{playerId}")
    @PreAuthorize("hasAnyAuthority('FriendsManagement', 'dev')")
    public ResponseEntity<PlayerDto> getFriend(
            @PathVariable("playerId") UUID playerId
    ) {
        final Player player = loadFriends.findPlayer(playerId);

        final PlayerDto response = PlayerDto.convertToDTO(player);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('FriendsManagement')")
    public ResponseEntity<PlayerDto> findPlayer(
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);

        final Player player = loadFriends.findPlayer(playerId);

        final PlayerDto response = PlayerDto.convertToDTO(player);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/nickname")
    @PreAuthorize("hasAnyAuthority('FriendsManagement', 'dev')")
    public ResponseEntity<List<PlayerDto>> searchByNickname(
            @RequestParam("searchTerm") final String nickname
    ) {
        final Friends friends = loadFriends.searchForFriend(nickname);

        final List<PlayerDto> response = friends.getFriends()
                .stream().map(PlayerDto::convertToDTO).toList();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{friendId}")
    @PreAuthorize("hasAuthority('FriendsManagement')")
    public ResponseEntity<Void> removeFriend(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("friendId") final UUID friendId
    ) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);
        removeFriend.removeFriend(friendId, playerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/lobbies")
    @PreAuthorize("hasAuthority('FriendsManagement')")
    public ResponseEntity<List<LobbyQuery>> showMyFriendsLobbies(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("UserId");

        UUID playerId = UUID.fromString(userId);
        LoadLobbiesQuery loadLobbiesQuery = new LoadLobbiesQuery(playerId);
        return ResponseEntity.status(HttpStatus.OK).body(loadFriendsLobbies.fetchFriendsLobbies(loadLobbiesQuery));

    }


}
