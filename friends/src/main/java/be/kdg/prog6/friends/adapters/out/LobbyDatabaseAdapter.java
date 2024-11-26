package be.kdg.prog6.friends.adapters.out;

import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.friends.adapters.out.entity.LobbyJpaEntity;
import be.kdg.prog6.friends.adapters.out.repository.LobbyJpaRepository;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import be.kdg.prog6.friends.port.out.LobbySavePort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Repository
public class LobbyDatabaseAdapter implements LobbySavePort, LobbyLoadPort {

    private final LobbyJpaRepository lobbyJpaRepository;

    public LobbyDatabaseAdapter(LobbyJpaRepository lobbyJpaRepository) {
        this.lobbyJpaRepository = lobbyJpaRepository;
    }

    @Override
    public Lobby loadLobbies(UUID playerId) {
       LobbyJpaEntity lobbyJpaEntity = lobbyJpaRepository.findAllByPlayerId(playerId);

        if (lobbyJpaEntity != null) {
            return lobbyJpaEntity.toDomain();
        } else {
            throw new EntityNotFoundException("Lobby with id " + playerId + " not found");
        }
    }

    @Override
    public void save(Lobby domain) {
        LobbyJpaEntity lobbyJpaEntity = toEntity(domain);
        lobbyJpaRepository.save(lobbyJpaEntity);
    }

    public LobbyJpaEntity toEntity(Lobby domain) {
        return new LobbyJpaEntity(
                domain.getLobbyId().id(),
                domain.getPlayerId(),
                domain.getGameId()
        );
    }
}
