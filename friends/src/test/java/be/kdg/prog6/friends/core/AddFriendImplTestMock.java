package be.kdg.prog6.friends.core;

import be.kdg.prog6.friends.domain.Address;
import be.kdg.prog6.friends.domain.Gender;
import be.kdg.prog6.friends.domain.Player;
import be.kdg.prog6.friends.port.in.AddFriend;
import be.kdg.prog6.friends.port.in.command.AddFriendCommand;
import be.kdg.prog6.friends.port.out.PlayerPort;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@Slf4j
@ExtendWith({MockitoExtension.class})
class AddFriendImplTestMock {
    @Mock
    private PlayerPort playerPortMock;

    @Test
    void shouldAddFriend() {
        Set<Player> friends = new HashSet<>(Arrays.asList(
                new Player(
                        new Player.PlayerId(UUID.randomUUID()),
                        "friend1",
                        "firstName1",
                        "lastName1",
                        Gender.MALE,
                        new Address(
                                new Address.AddressId(UUID.randomUUID()),
                                "street1",
                                "city1",
                                "country1",
                                "houseNumber1"
                        )
                ),
                new Player(
                        new Player.PlayerId(UUID.randomUUID()),
                        "friend2",
                        "firstName2",
                        "lastName2",
                        Gender.MALE,
                        new Address(
                                new Address.AddressId(UUID.randomUUID()),
                                "street2",
                                "city2",
                                "country2",
                                "houseNumber2"
                        )
                )
        ));

        final Player player = new Player(
                new Player.PlayerId(UUID.randomUUID()),
                "player1",
                "firstName3",
                "lastName3",
                Gender.FEMALE,
                new Address(
                        new Address.AddressId(UUID.randomUUID()),
                        "street3",
                        "city3",
                        "country3",
                        "houseNumber3"
                )
        );

        player.setFriends(friends);

        final Player newFriend = new Player(
                new Player.PlayerId(UUID.randomUUID()),
                "player2",
                "firstName4",
                "lastNam43",
                Gender.FEMALE,
                new Address(
                        new Address.AddressId(UUID.randomUUID()),
                        "street4",
                        "city4",
                        "country4",
                        "houseNumber4"
                )
        );

        final AddFriendCommand command = new AddFriendCommand(
                player.getId().id(),
                newFriend.getId().id()
        );

        when(playerPortMock.findByIdWithFriends(command.playerId())).thenReturn(player);

        when(playerPortMock.findById(command.friendId())).thenReturn(newFriend);

        final AddFriend addFriend = new AddFriendImpl(
                playerPortMock
        );

        final Player updatedPlayer = addFriend.addFriend(command);

        verify(playerPortMock).savePlayerWithFriends(player);

        assertEquals(3, updatedPlayer.getFriends().size());
    }

    @Test
    void shouldThrowAnErrorWhenAddingExistingFriend() {
        final Player newFriend = new Player(
                new Player.PlayerId(UUID.randomUUID()),
                "player2",
                "firstName4",
                "lastName3",
                Gender.FEMALE,
                new Address(
                        new Address.AddressId(UUID.randomUUID()),
                        "street4",
                        "city4",
                        "country4",
                        "houseNumber4"
                )
        );

        Set<Player> friends = new HashSet<>(Arrays.asList(
                newFriend,
                new Player(
                        new Player.PlayerId(UUID.randomUUID()),
                        "friend2",
                        "firstName2",
                        "lastName2",
                        Gender.MALE,
                        new Address(
                                new Address.AddressId(UUID.randomUUID()),
                                "street2",
                                "city2",
                                "country2",
                                "houseNumber2"
                        )
                )
        ));

        final Player player = new Player(
                new Player.PlayerId(UUID.randomUUID()),
                "player1",
                "firstName3",
                "lastName3",
                Gender.FEMALE,
                new Address(
                        new Address.AddressId(UUID.randomUUID()),
                        "street3",
                        "city3",
                        "country3",
                        "houseNumber3"
                )
        );

        player.setFriends(friends);

        final AddFriendCommand command = new AddFriendCommand(
                player.getId().id(),
                newFriend.getId().id()
        );

        when(playerPortMock.findByIdWithFriends(command.playerId())).thenReturn(player);
        when(playerPortMock.findById(command.friendId())).thenReturn(newFriend);

        final AddFriend addFriend = new AddFriendImpl(playerPortMock);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addFriend.addFriend(command));

        assertEquals("Friend with ID " + newFriend.getId().id() + " is already in the friends set.", exception.getMessage());

        verify(playerPortMock, never()).savePlayerWithFriends(any(Player.class));
    }

}