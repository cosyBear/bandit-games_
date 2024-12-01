import be.kdg.prog6.common.events.util.LobbyIsFullException;
import be.kdg.prog6.lobby.LobbyBondedContextApplication;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.common.domain.LobbyStatus;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = LobbyBondedContextApplication.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class LobbyTestUseCase {


    private LobbyId lobbyId = new LobbyId(UUID.fromString("582e8f3e-54c6-4f34-b67f-df3059b9b47a"));
    private UUID gameID = UUID.fromString("223e4567-e89b-12d3-a456-426614174001");
    private UUID lobbyAdmin = UUID.fromString("323e4567-e89b-12d3-a456-426614174002");
    private UUID guestPlayer = UUID.fromString("423e4567-e89b-12d3-a456-426614174003");
    private LocalDateTime createdAt = LocalDateTime.of(2024, 11, 22, 12, 0); // Specific date and time
    private LobbyStatus lobbyStatus = LobbyStatus.Created; // Default status as ACTIVE

    private Lobby lobby;

    @MockBean
    private LobbyLoadPort lobbyLoadPort;


    @MockBean
    private LobbySavePort lobbySavePort;


    @BeforeEach
    void setup() {
        Lobby lobby = Lobby.createLobby(lobbyId, gameID, lobbyAdmin);

        Mockito.when(lobbyLoadPort.loadLobbyById(lobbyId))
                .thenReturn(lobby);

        Mockito.doNothing().when(lobbySavePort).save(any(Lobby.class));
    }


    @Test
    public void shouldInitializeLobbyWithCorrectValuesWithOutGuestPlayer() {
        Lobby lobby = Lobby.createLobby(lobbyId, gameID, lobbyAdmin);

        assertEquals(lobbyId, lobby.getLobbyId());
        assertEquals(gameID, lobby.getGameId());
        assertEquals(lobbyAdmin, lobby.getLobbyAdmin());
        assertNull(lobby.getGuestPlayer());
    }

    @Test
    public void shouldSaveLobbyAfterAddingPlayer() {
        // Arrange:
        Lobby lobby = lobbyLoadPort.loadLobbyById(lobbyId);

        // Act:
        lobby.addGuestPlayerToLobby(guestPlayer);
        lobbySavePort.save(lobby);

        // Assert:
        assertEquals(guestPlayer, lobby.getGuestPlayer(), "Guest player should be added.");
        assertEquals(LobbyStatus.Full, lobby.getLobbyStatus(), "Lobby status should be Full.");

        Mockito.verify(lobbySavePort).save(lobby);
    }


    @Test
    public void shouldThrowExceptionWhenAddingPlayerToFullLobby() {
        // Arrange:
        Lobby lobby = lobbyLoadPort.loadLobbyById(lobbyId);
        lobby.addGuestPlayerToLobby(guestPlayer);

        // Act & Assert:
        UUID anotherPlayer = UUID.randomUUID();
        LobbyIsFullException exception =
                org.junit.jupiter.api.Assertions.assertThrows(LobbyIsFullException.class, () -> {
                    lobby.addGuestPlayerToLobby(anotherPlayer);
                });

        // Assert:
        assertEquals("The lobby is full", exception.getMessage(), "Exception message should match expected output.");
    }







}
