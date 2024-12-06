package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.lobby.adapters.out.Repository.LobbyRepository;
import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import be.kdg.prog6.lobby.adapters.out.entity.RequestAccessEntity;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.RequestAccess;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import be.kdg.prog6.common.events.util.LobbyDontExistException;
import be.kdg.prog6.lobby.util.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LobbyDatabaseAdapter implements LobbyLoadPort, LobbySavePort {



    private final LobbyRepository lobbyRepository;

    public LobbyDatabaseAdapter(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    @Override
    public Lobby loadLobbyById(LobbyId lobbyId) {
        LobbyEntity lobbyEntity = lobbyRepository.loadByLobbyId(lobbyId.lobbyId());

        if(lobbyEntity == null) {
            throw new LobbyDontExistException("lobbyEntity with id:" +lobbyId.lobbyId().toString() + " not found");
        }


        return Mapper.toDomain(lobbyEntity);
    }

    @Override
    public List<RequestAccess> loadRequestAccessForLobby(LobbyId lobbyId) {
        List<RequestAccessEntity> requestAccessEntities = lobbyRepository.loadCreatedRequests(lobbyId.lobbyId());

        if (requestAccessEntities == null || requestAccessEntities.isEmpty()) {
            return List.of();
        }

        return requestAccessEntities.stream()
                .map(Mapper::toDomainRequestAccess)
                .toList();
    }


    @Override
    public void save(Lobby lobby) {
        LobbyEntity entity = Mapper.mapToEntity(lobby);
        lobbyRepository.save(entity);
    }





}
