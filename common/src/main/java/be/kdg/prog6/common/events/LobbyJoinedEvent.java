package be.kdg.prog6.common.events;

import java.util.UUID;

public record LobbyJoinedEvent(UUID lobbyId , String Status ) {
}
