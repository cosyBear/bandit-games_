package be.kdg.prog6.lobby.util;

import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import be.kdg.prog6.lobby.adapters.out.entity.LobbyStatusEntity;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.LobbyStatus;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;

public class Mapper {


    public static Lobby toDomain(LobbyEntity entity) {
        return new Lobby(new LobbyId(entity.getLobbyId()), entity.getGameId(),
                entity.getLobbyAdmin(), entity.getGuestPlayer(),
                entity.getCreatedAt(),
                LobbyStatus.valueOf(entity.getLobbyStatusEntity().toString()));

    }

    public static LobbyEntity mapToEntity(Lobby domain) {
        return new LobbyEntity(domain.getLobbyId().lobbyId(),
                domain.getGameId(), domain.getLobbyAdmin(), domain.getGuestPlayer(),
                LobbyStatusEntity.valueOf(domain.getLobbyStatus().toString()),
                domain.getCreatedAt());
    }

    public static LobbyUpdateQuery mapToUpdateQuery(Lobby domain) {

        return new LobbyUpdateQuery(domain.getLobbyId().lobbyId(), domain.getGameId(), domain.getLobbyAdmin(), domain.getGuestPlayer(),
                domain.getCreatedAt(), domain.getLobbyStatus().toString());

    }


}
