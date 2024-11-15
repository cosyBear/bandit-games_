package be.kdg.prog6.friends.adapters.dto;

import be.kdg.prog6.friends.domain.Gender;
import be.kdg.prog6.friends.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private UUID id;
    private String nickname;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Set<PlayerDto> friends = new HashSet<>();

    public PlayerDto(UUID id, String nickname, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public static PlayerDto convertToDTO(Player player) {
        return new PlayerDto(
                player.getId().id(),
                player.getNickname(),
                player.getFirstName(),
                player.getLastName(),
                player.getGender(),
                player.getFriends().stream().map(friend -> {
                    return new PlayerDto(
                            friend.getId().id(),
                            friend.getNickname(),
                            friend.getFirstName(),
                            friend.getLastName(),
                            friend.getGender()
                    );
                }).collect(Collectors.toSet())
        );
    }
}
