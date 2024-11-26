package be.kdg.prog6.friends.port.out;
import be.kdg.prog6.friends.domain.Lobby;
import java.util.UUID;

public interface LobbyLoadPort {

    Lobby loadLobbies(UUID playerId);

}
