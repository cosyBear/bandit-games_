package be.kdg.prog6.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Data
public class Friends {
    private Set<Player> friends = new HashSet<>();

    public void addFriend(Player player) {
        if (this.friends.contains(player)) {
            throw new IllegalArgumentException("Friend with ID " + player.getId().id() + " is already in the friends set.");
        }
        this.friends.add(player);
    }


    public void removeFriend(Player player) {
        if (this.friends.contains(player)) {
            this.friends.remove(player);
        } else {
            throw new IllegalArgumentException("Friend with ID " + player.getId().id() + "is not a friend of this player.");
        }
    }

}
