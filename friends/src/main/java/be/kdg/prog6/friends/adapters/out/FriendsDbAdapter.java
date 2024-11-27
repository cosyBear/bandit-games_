package be.kdg.prog6.friends.adapters.out;

import be.kdg.prog6.common.exception.EntityNotFoundException;
import be.kdg.prog6.friends.adapters.out.entity.FriendsId;
import be.kdg.prog6.friends.adapters.out.entity.FriendsJpaEntity;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import be.kdg.prog6.friends.adapters.out.repository.FriendsJpaRepository;
import be.kdg.prog6.friends.adapters.out.repository.PlayerJpaRepository;
import be.kdg.prog6.friends.domain.Friends;
import be.kdg.prog6.friends.port.out.FriendsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FriendsDbAdapter implements FriendsPort {
    private final PlayerJpaRepository playerJpaRepository;
    private final FriendsJpaRepository friendsJpaRepository;

    @Override
    public Friends findAll(UUID playerId) {
        final List<FriendsJpaEntity> friendsJpaEntities = friendsJpaRepository.findAllByPlayerId(playerId);

        final List<PlayerJpaEntity> players = friendsJpaEntities.stream().map(
                friendsJpaEntity -> playerJpaRepository.findById(friendsJpaEntity.getId().getFriendId())
                        .orElseThrow(() -> new EntityNotFoundException("Friend was not found"))).toList();

        return new Friends(
                players.stream().map(PlayerJpaEntity::convertToDomain).collect(Collectors.toSet())
        );
    }

    @Override
    public void saveAllFriends(Friends friends, UUID playerId) {
        List<UUID> friendsIds = friends.getFriends().stream().map(player -> player.getId().id()).toList();
        List<FriendsJpaEntity> friendsJpaEntities = new ArrayList<>();

        for(UUID friendId : friendsIds){
            final FriendsJpaEntity friendsJpaEntity = friendsJpaRepository.findByPlayerAndFriendId(playerId, friendId)
                    .orElse(
                            new FriendsJpaEntity(
                                    new FriendsId(playerId, friendId)
                            )
                    );

            friendsJpaEntities.add(friendsJpaEntity);
        }

        friendsJpaRepository.saveAll(friendsJpaEntities);
    }

    @Override
    public void deleteFriend(Friends friends, UUID playerId) {
        List<UUID> updatedFriendsIds = friends.getFriends().stream().map(player -> player.getId().id()).toList();
        List<FriendsJpaEntity> currentFriends = friendsJpaRepository
                .findAllByPlayerId(playerId);

        for(FriendsJpaEntity friend : currentFriends){
            if(!updatedFriendsIds.contains(friend.getId().getFriendId())){
                friendsJpaRepository.delete(friend);
            }
        }

    }
}
