package kdg.be.prog6.domain;



import kdg.be.prog6.domain.ids.LobbyId;
import kdg.be.prog6.util.LobbyIsFullException;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Lobby {

    private LobbyId lobbyId;
    private UUID gameId;
    private UUID lobbyAdmin;
    private UUID guestPlayer;
    private LocalDateTime createdAt;
    private LobbyStatus lobbyStatus;
    private static Integer maxPlayer = 2;

    public Lobby(LobbyId lobbyId, UUID gameId, UUID lobbyAdmin, LobbyStatus lobbyStatus) {
        this.lobbyId = lobbyId;
        this.gameId = gameId;
        this.lobbyAdmin = lobbyAdmin;
        this.lobbyStatus = lobbyStatus;
        this.createdAt = LocalDateTime.now();
    }

    public Lobby(LobbyId lobbyId, UUID gameId, UUID lobbyAdmin, UUID guestPlayer, LocalDateTime createdAt, LobbyStatus lobbyStatus) {
        this.lobbyId = lobbyId;
        this.gameId = gameId;
        this.lobbyAdmin = lobbyAdmin;
        this.guestPlayer = guestPlayer;
        this.createdAt = createdAt;
        this.lobbyStatus = lobbyStatus;
    }

    //ToDo leave and join the lobby

    public static Lobby createLobby(LobbyId lobbyId, UUID gameId, UUID lobbyAdmin) {
        if (gameId == null || lobbyAdmin == null) {
            throw new IllegalArgumentException("Game ID and Lobby Admin cannot be null");
        }
        Lobby lobby = new Lobby(lobbyId, gameId, lobbyAdmin, LobbyStatus.Created);
        lobby.updateLobbyStatusBasedOnState();
        return lobby;
    }

    public void addGuestPlayerToLobby(UUID guestPlayer) {
        if (isLobbyFull()) {
            throw new LobbyIsFullException("The lobby is full");
        }

        if (!isAdminMissing()) {
            this.guestPlayer = guestPlayer;
        } else {
            this.lobbyAdmin = guestPlayer;

        }
        updateLobbyStatusBasedOnState();
    }


    public void leaveLobby(UUID guestPlayer){
        this.guestPlayer = null;
    }


    private boolean isLobbyFull() {
        return this.guestPlayer != null && this.lobbyAdmin != null;
    }

    private boolean isAdminMissing() {
        return this.lobbyAdmin == null;
    }


    public void updateLobbyStatusBasedOnState() {
        if (isLobbyFull()) {
            this.lobbyStatus = LobbyStatus.Full;
        } else if (this.lobbyAdmin != null) {
            this.lobbyStatus = LobbyStatus.Created;
        }
    }


}
