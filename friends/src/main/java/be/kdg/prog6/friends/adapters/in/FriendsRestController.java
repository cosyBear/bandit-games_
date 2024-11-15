package be.kdg.prog6.friends.adapters.in;

import be.kdg.prog6.friends.adapters.dto.PlayerDto;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.LoadFriends;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendsRestController {
    private final AddFriend addFriend;
    private final LoadFriends loadFriends;

    @PostMapping
    public ResponseEntity<PlayerDto> addNewFriend(
            @RequestBody AddFriendCommand command
    ) {
        final Player player = addFriend.addFriend(command);

        final PlayerDto response = PlayerDto.convertToDTOWithFriends(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<PlayerDto>> getAllFriends(
            @PathVariable("playerId") UUID playerId
    ) {
        final List<Player> friends = loadFriends.getAllFriends(playerId);

        final List<PlayerDto> response = friends.stream().map(PlayerDto::convertToDTO).toList();

        return ResponseEntity.ok(response);
    }
}
