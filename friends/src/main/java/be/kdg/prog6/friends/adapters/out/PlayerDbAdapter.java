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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
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

    @Override
    public void savePlayerWithFriends(Player player) {
        final PlayerJpaEntity playerJpaEntity = playerJpaRepository.findByIdWithFriends(player.getId().id())
                .orElseThrow(() -> new EntityNotFoundException("Player was not found"));

        Set<PlayerJpaEntity> newFriends = player.getFriends().stream()
                .map(friend -> playerJpaRepository.findById(friend.getId().id())
                        .orElseThrow(() -> new EntityNotFoundException("Friend not found: " + friend.getId().id())))
                .collect(Collectors.toSet());

        playerJpaEntity.getFriends().removeIf(existingFriend -> !newFriends.contains(existingFriend));

        newFriends.forEach(newFriend -> {
            if (!playerJpaEntity.getFriends().contains(newFriend)) {
                playerJpaEntity.addFriend(newFriend);
            }
        });

        playerJpaRepository.save(playerJpaEntity);
    }
}
