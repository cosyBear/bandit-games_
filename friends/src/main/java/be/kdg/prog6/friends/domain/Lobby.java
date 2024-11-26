package be.kdg.prog6.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Lobby {

    private LobbyId id;
    private UUID gameId;
    private Player player;


}
