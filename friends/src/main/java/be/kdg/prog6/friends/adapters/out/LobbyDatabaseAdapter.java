package be.kdg.prog6.friends.adapters.out;

import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.friends.adapters.out.entity.LobbyJpaEntity;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import be.kdg.prog6.friends.adapters.out.repository.LobbyJpaRepository;
import be.kdg.prog6.friends.adapters.out.repository.PlayerJpaRepository;
import be.kdg.prog6.friends.domain.Lobby;
import be.kdg.prog6.friends.port.out.LobbyLoadPort;
import be.kdg.prog6.friends.port.out.LobbySavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LobbyDatabaseAdapter implements LobbySavePort, LobbyLoadPort {

    private final LobbyJpaRepository lobbyJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;

    @Override
    public Lobby loadLobbies(UUID playerId) {
       final LobbyJpaEntity lobbyJpaEntity = lobbyJpaRepository.findAllByPlayerId(playerId)
               .orElseThrow(() -> new EntityNotFoundException("Lobby was not found for player " + playerId));

       return lobbyJpaEntity.toDomain();
    }

    @Override
    public void save(Lobby domain) {
        final PlayerJpaEntity playerJpaEntity = playerJpaRepository.findById(domain.getPlayer().getId().id())
                .orElseThrow(() -> new EntityNotFoundException("Player with id " + domain.getPlayer().getId().id() + " not found"));

        final LobbyJpaEntity lobbyJpaEntity = LobbyJpaEntity.fromDomain(domain, playerJpaEntity);
        lobbyJpaRepository.save(lobbyJpaEntity);
    }
}
