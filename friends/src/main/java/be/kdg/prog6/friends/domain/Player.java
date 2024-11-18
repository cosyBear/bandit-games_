package be.kdg.prog6.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Player {

    private PlayerId id;
    private String nickname;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Address address;
    private Set<Player> friends = new HashSet<>();

    public record PlayerId(UUID id){}

    public Player(PlayerId id, String nickname, String firstName, String lastName, Gender gender, Address address) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
    }

    public void addFriend(Player player) {
        if (this.friends.contains(player)) {
            throw new IllegalArgumentException("Friend with ID " + player.getId().id() + " is already in the friends set.");
        }
        this.friends.add(player);
    }


    public void removeFriend(Player player){
        this.friends.remove(player);
    }
}
