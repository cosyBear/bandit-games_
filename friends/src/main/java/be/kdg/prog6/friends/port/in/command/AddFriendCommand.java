package be.kdg.prog6.friends.port.in.command;

import java.util.UUID;

public record AddFriendCommand(UUID playerId, UUID friendId) {
}
