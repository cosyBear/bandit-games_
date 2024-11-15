package be.kdg.prog6.friends.adapters.in;

import be.kdg.prog6.friends.adapters.dto.PlayerDto;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendsRestController {
    private final AddFriend addFriend;

    @PostMapping
    public ResponseEntity<PlayerDto> addNewFriend(
            @RequestBody AddFriendCommand command
    ) {
        final Player player = addFriend.addFriend(command);

        final PlayerDto response = PlayerDto.convertToDTO(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
