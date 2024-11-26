package kdg.be.prog6.util;


import kdg.be.prog6.adapters.out.entity.LobbyEntity;
import kdg.be.prog6.adapters.out.entity.LobbyStatusEntity;
import kdg.be.prog6.domain.Lobby;
import kdg.be.prog6.domain.LobbyStatus;
import kdg.be.prog6.domain.ids.LobbyId;
import kdg.be.prog6.port.in.Query.LobbyUpdateQuery;

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
