package be.kdg.prog6.friends.adapters.out;

import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import be.kdg.prog6.friends.adapters.out.repository.PlayerJpaRepository;
import be.kdg.prog6.friends.domain.Friends;
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
    public Friends searchForFriend(String nickName) {
        final List<PlayerJpaEntity> playerJpaEntities = playerJpaRepository.searchAllByNicknameIgnoreCase(nickName);

        return new Friends(playerJpaEntities.stream().map(PlayerJpaEntity::convertToDomain).collect(Collectors.toSet()));
    }
}
