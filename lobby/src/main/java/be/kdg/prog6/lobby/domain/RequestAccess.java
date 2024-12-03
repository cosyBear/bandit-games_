package be.kdg.prog6.lobby.domain;

import be.kdg.prog6.lobby.domain.ids.LobbyId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAccess {


    private UUID guestPlayerId;
    private RequestStatus requestStatus;




}
