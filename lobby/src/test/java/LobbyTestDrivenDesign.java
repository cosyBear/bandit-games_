import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.common.domain.LobbyStatus;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class LobbyTestDrivenDesign {


    private LobbyId lobbyId = new LobbyId(UUID.fromString("582e8f3e-54c6-4f34-b67f-df3059b9b47a"));
    private UUID playerID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private UUID gameID = UUID.fromString("223e4567-e89b-12d3-a456-426614174001");
    private UUID lobbyAdmin = UUID.fromString("323e4567-e89b-12d3-a456-426614174002");
    private UUID guestPlayer = UUID.fromString("423e4567-e89b-12d3-a456-426614174003");
    private LocalDateTime createdAt = LocalDateTime.of(2024, 11, 22, 12, 0); // Specific date and time
    private LobbyStatus lobbyStatus = LobbyStatus.Created; // Default status as ACTIVE

    Lobby lobby;

    @BeforeEach
    void setup(){
        Lobby lobby = Lobby.createLobby(lobbyId, gameID, lobbyAdmin);
    }


    @Test
    public void shouldInitializeLobbyWithCorrectValuesWithOutGuestPlayer() {
        //arrange & act
        Lobby lobby = Lobby.createLobby(lobbyId, gameID, lobbyAdmin);

        //assert
        assertEquals(lobbyId, lobby.getLobbyId());        // Expected lobbyId matches
        assertEquals(gameID, lobby.getGameId());          // Expected gameId matches
        assertEquals(lobbyAdmin, lobby.getLobbyAdmin());  // Expected lobbyAdmin matches
        assertNull(lobby.getGuestPlayer());               // Guest player should be null
    }


    @Test
    public void shouldAddPlayerToLobby(LobbyId lobbyId , UUID guestPlayer) {

    }







}
