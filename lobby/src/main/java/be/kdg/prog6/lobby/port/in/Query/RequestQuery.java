package be.kdg.prog6.lobby.port.in.Query;

import be.kdg.prog6.lobby.domain.RequestStatus;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestQuery {

    private LobbyId lobbyId;
    private UUID guestPlayerId;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

}



