package be.kdg.prog6.friends.adapters.out;

import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import be.kdg.prog6.friends.adapters.out.repository.PlayerJpaRepository;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.out.PlayerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor @Slf4j
@Repository
public class PlayerDbAdapter implements PlayerPort {
    private final PlayerJpaRepository playerJpaRepository;

    @Override
    public Player findById(UUID uuid) {
        final PlayerJpaEntity playerJpaEntity = playerJpaRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Player was not found"));

        return playerJpaEntity.convertToDomain();
    }

    @Override
    public void saveNewFriend(Player player, UUID newFriendId) {
        final PlayerJpaEntity playerJpaEntity = playerJpaRepository.findByIdWithFriends(player.getId().id())
                .orElseThrow(() -> new EntityNotFoundException("Player was not found"));

        final PlayerJpaEntity newFriendJpaEntity = playerJpaRepository.findById(newFriendId)
                .orElseThrow(() -> new EntityNotFoundException("New friend was not found"));

        playerJpaEntity.addFriend(newFriendJpaEntity);
    }

    @Override
    public Player findByIdWithFriends(UUID uuid) {
        final PlayerJpaEntity playerJpaEntity = playerJpaRepository.findByIdWithFriends(uuid)
                .orElseThrow(() -> new EntityNotFoundException("PLayer was not found"));

        return playerJpaEntity.convertToDomainWithFriends();
    }

    @Override
    public List<Player> findAllFriends(UUID playerId) {
        final List<PlayerJpaEntity> friends = playerJpaRepository.findAllFriends(playerId);

        return friends.stream().map(PlayerJpaEntity::convertToDomain).toList();
    }

    @Override
    public List<Player> searchForFriend(String nickName) {
        final List<PlayerJpaEntity> friends = playerJpaRepository.findByNickNameIgnoringCase(nickName);

        return friends.stream().map(PlayerJpaEntity::convertToDomain).toList();
    }
}
