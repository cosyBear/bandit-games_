package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.adapters.out.entity.AddressJpaEntity;
import be.kdg.prog6.friends.adapters.out.entity.FriendsId;
import be.kdg.prog6.friends.adapters.out.entity.FriendsJpaEntity;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import be.kdg.prog6.friends.adapters.out.repository.AddressJpaRepository;
import be.kdg.prog6.friends.adapters.out.repository.FriendsJpaRepository;
import be.kdg.prog6.friends.adapters.out.repository.PlayerJpaRepository;
import be.kdg.prog6.friends.domain.Gender;
import be.kdg.prog6.friends.port.in.RemoveFriend;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional
class RemoveFriendImplTestIntegration extends AbstractDatabaseAndRabbitTest {


    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Autowired
    private AddressJpaRepository addressJpaRepository;
    @Autowired
    private FriendsJpaRepository friendsJpaRepository;
    @Autowired
    private RemoveFriend sut;

    private UUID playerId = null;
    private UUID friend2Id = null;
    private UUID friend1Id= null;

    @BeforeEach
    void setup() {
        final PlayerJpaEntity friend1 = playerJpaRepository.save(
                new PlayerJpaEntity(
                        UUID.randomUUID(),
                        "friend1",
                        "firstName1",
                        "lastName1",
                        Gender.MALE.toString()
                )
        );

        friend1Id = friend1.getId();

        friend1.setAddressJpaEntity(addressJpaRepository.save(new AddressJpaEntity(
                UUID.randomUUID(),
                "street1",
                "city1",
                "country1",
                "houseNumber1",
                friend1
        )));

        playerJpaRepository.save(friend1);

        final PlayerJpaEntity friend2 = playerJpaRepository.save(
                new PlayerJpaEntity(
                        UUID.randomUUID(),
                        "friend2",
                        "firstName2",
                        "lastName2",
                        Gender.MALE.toString()
                )
        );

        friend2Id = friend2.getId();

        friend2.setAddressJpaEntity(addressJpaRepository.save(new AddressJpaEntity(
                UUID.randomUUID(),
                "street2",
                "city2",
                "country2",
                "houseNumber2",
                friend2
        )));

        playerJpaRepository.save(friend2);


        final PlayerJpaEntity player = new PlayerJpaEntity(
                UUID.randomUUID(),
                "player1",
                "firstName3",
                "lastName3",
                Gender.FEMALE.toString()
        );

        playerJpaRepository.save(player);

        playerId = player.getId();

        player.setAddressJpaEntity(addressJpaRepository.save(new AddressJpaEntity(
                UUID.randomUUID(),
                "street3",
                "city3",
                "country3",
                "houseNumber3",
                player
        )));

        playerJpaRepository.save(player);

        final FriendsJpaEntity friendsJpaEntity = new FriendsJpaEntity(
                new FriendsId(playerId, friend2Id)
        );

        friendsJpaRepository.save(friendsJpaEntity);

    }


    @Test
    void shouldRemoveFriendOfPlayer() {
        sut.removeFriend(friend2Id, playerId);

        final List<FriendsJpaEntity> friendsJpaEntities = friendsJpaRepository.findAllByPlayerId(playerId);

        assertEquals(0, friendsJpaEntities.size());
    }

    @Test
    void shouldNotRemoveNotExistingFriend(){
        assertThrows(IllegalArgumentException.class, () -> sut.removeFriend(friend1Id, playerId));

        final List<FriendsJpaEntity> friendsJpaEntities = friendsJpaRepository.findAllByPlayerId(playerId);

        assertEquals(1, friendsJpaEntities.size());
    }

    @AfterEach
    void cleanup(){
        playerJpaRepository.deleteAll();
        friendsJpaRepository.deleteAll();
    }
}