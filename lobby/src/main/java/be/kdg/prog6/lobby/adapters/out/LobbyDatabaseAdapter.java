package be.kdg.prog6.lobby.adapters.out;

import be.kdg.prog6.lobby.adapters.out.Repository.LobbyRepository;
import be.kdg.prog6.lobby.adapters.out.entity.LobbyEntity;
import be.kdg.prog6.lobby.domain.Lobby;
import be.kdg.prog6.lobby.domain.ids.LobbyId;
import be.kdg.prog6.lobby.port.out.LobbyLoadPort;
import be.kdg.prog6.lobby.port.out.LobbySavePort;
import be.kdg.prog6.common.events.util.LobbyDontExistException;
import be.kdg.prog6.lobby.util.Mapper;
import org.springframework.stereotype.Component;

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
    public void save(Lobby lobby) {
        LobbyEntity entity = Mapper.mapToEntity(lobby);
        lobbyRepository.save(entity);
    }





}
