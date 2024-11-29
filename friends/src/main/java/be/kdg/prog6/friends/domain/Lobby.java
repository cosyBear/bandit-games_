package be.kdg.prog6.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import be.kdg.prog6.common.domain.LobbyStatus;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Lobby {

    private LobbyId id;
    private UUID gameId;
    private Player player;
    private LobbyStatus lobbyStatus;


    public void updateLobbyStatus(LobbyStatus lobbyStatus) {
        this.lobbyStatus = lobbyStatus;
    }

}
