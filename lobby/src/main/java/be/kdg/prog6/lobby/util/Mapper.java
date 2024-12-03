package be.kdg.prog6.lobby.util;

import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import be.kdg.prog6.lobby.adapters.out.entity.LobbyStatusEntity;
import be.kdg.prog6.lobby.adapters.out.entity.RequestAccessEntity;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.common.domain.LobbyStatus;
import be.kdg.prog6.lobby.domain.RequestAccess;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.in.Query.LobbyUpdateQuery;
import be.kdg.prog6.lobby.port.in.Query.RequestQuery;

import java.util.stream.Collectors;

public class Mapper {


    public static Lobby toDomain(LobbyEntity entity) {
        return new Lobby(
                new LobbyId(entity.getLobbyId()),
                entity.getGameId(),
                entity.getLobbyAdmin(),
                entity.getGuestPlayer(),
                entity.getCreatedAt(),
                LobbyStatus.valueOf(entity.getLobbyStatusEntity().toString()),
                entity.getRequests().stream()
                        .map(Mapper::toDomainRequestAccess)
                        .collect(Collectors.toSet())
        );
    }

    // Map from Domain Lobby to LobbyEntity
    public static LobbyEntity mapToEntity(Lobby domain) {
        return new LobbyEntity(
                domain.getLobbyId().lobbyId(),
                domain.getGameId(),
                domain.getLobbyAdmin(),
                domain.getGuestPlayer(),
                LobbyStatusEntity.valueOf(domain.getLobbyStatus().toString()),
                domain.getCreatedAt(),
                domain.getAccessRequests().stream()
                        .map(Mapper::toEntityRequestAccess)
                        .collect(Collectors.toSet())
        );
    }

    // Map from Domain RequestAccess to RequestAccessEntity
    public static RequestAccessEntity toEntityRequestAccess(RequestAccess request) {
        return new RequestAccessEntity(
                request.getGuestPlayerId(),
                request.getRequestStatus()
        );
    }

    // Map from RequestAccessEntity to Domain RequestAccess
    public static RequestAccess toDomainRequestAccess(RequestAccessEntity entity) {
        return new RequestAccess(
                entity.getGuestPlayerId(),
                entity.getRequestStatus()
        );
    }

    public static RequestQuery RequestToQuery(RequestAccess domain, LobbyId lobbyId) {
        return new RequestQuery(
                lobbyId,
                domain.getGuestPlayerId(),
                domain.getRequestStatus()
        );
    }


    public static LobbyUpdateQuery mapToUpdateQuery(Lobby domain) {

        return new LobbyUpdateQuery(domain.getLobbyId().lobbyId(), domain.getGameId(), domain.getLobbyAdmin(), domain.getGuestPlayer(),
                domain.getCreatedAt(), domain.getLobbyStatus().toString());

    }


}
