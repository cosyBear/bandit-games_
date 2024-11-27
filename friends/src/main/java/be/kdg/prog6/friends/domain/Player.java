package be.kdg.prog6.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private PlayerId id;
    private String nickname;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Address address;

    public record PlayerId(UUID id) {
    }

    public Player(PlayerId id, String nickname, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
