package be.kdg.prog6.friends.port.in;

import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;

public interface AddFriend {
    Player addFriend(AddFriendCommand command);
}
