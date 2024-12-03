package be.kdg.prog6.lobby.adapters.out.entity;
import be.kdg.prog6.lobby.domain.RequestStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAccessEntity {

    private UUID guestPlayerId;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
}
