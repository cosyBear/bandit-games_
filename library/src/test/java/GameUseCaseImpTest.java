import be.kdg.prog6.common.events.util.AchievementAlreadyEarnedException;
import be.kdg.prog6.common.events.util.AchievementNotFoundException;
import be.kdg.prog6.common.events.util.GameAlreadyMarkedAsFavoriteException;
import be.kdg.prog6.common.events.util.GameNotFoundException;
import be.kdg.prog6.libraryBoundedContext.LibraryBoundedContextApplication;
import be.kdg.prog6.libraryBoundedContext.domain.Achievement;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.EarnAchievementCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.GameCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.game.GameUseCase;
import be.kdg.prog6.libraryBoundedContext.port.in.gameQuery.GameQuery;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import be.kdg.prog6.libraryBoundedContext.port.out.LibrarySavePort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = LibraryBoundedContextApplication.class)
class GameUseCaseImpTest {


    @MockBean
    private LibraryLoadPort libraryLoadPort;

    @MockBean
    private LibrarySavePort librarySavePort;

    @Autowired
    private GameUseCase sut;

    private static final String gameName = "Chess Master";
    private static final PlayerId playerId = new PlayerId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
    private static final GameId gameId = new GameId(UUID.fromString("e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1"));

    @BeforeEach
    void setUp() {
        Library library = TestLibraryData.createLibraryWithChessMaster();

        when(libraryLoadPort.fetchLibraryWithGamesByNamePattern(playerId, gameName))
                .thenReturn(library);

        doNothing().when(librarySavePort).save(any(Library.class));
    }

    @Test
    void markGameAsFavourite() {
        // Arrange
        GameCommand command = new GameCommand(playerId, gameName, gameId.gameId());

        // Act
        GameQuery result = sut.markGameAsFavourite(command);

        // Assert
        ArgumentCaptor<Library> libraryCaptor = ArgumentCaptor.forClass(Library.class);
        verify(librarySavePort).save(libraryCaptor.capture());

        Library savedLibrary = libraryCaptor.getValue();
        assertTrue(savedLibrary.findGameById(gameId).isGameMarkedAsFavorite(), "Game should be marked as favorite");

        assertTrue(result.isFavourite(), "Returned query should reflect favorite status");
    }

    @Test
    void givePlayerAnAchievement() {
        // Arrange
        EarnAchievementCommand command = new EarnAchievementCommand(
                playerId,
                gameName,
                gameId.gameId(),
                "First Blood"
        );

        // Act
        GameQuery result = sut.givePlayerAnAchievement(command);

        // Assert
        ArgumentCaptor<Library> libraryCaptor = ArgumentCaptor.forClass(Library.class);
        verify(librarySavePort).save(libraryCaptor.capture());

        Library savedLibrary = libraryCaptor.getValue();
        assertTrue(
                savedLibrary.findGameById(gameId).getAchievementList().stream()
                        .anyMatch(a -> a.getAchievementName().equals("First Blood") && a.isAchieved()),
                "Achievement should be marked as achieved"
        );

        assertTrue(result.getAchievementList().stream()
                        .anyMatch(a -> a.getAchievementName().equals("First Blood") && a.isAchieved()),
                "Returned query should reflect the achieved status");
    }


    @Test
    void markGameAsFavourite_GameNotFound_ShouldThrowException() {
        // Arrange
        GameCommand command = new GameCommand(playerId, "Nonexistent Game", UUID.randomUUID());

        when(libraryLoadPort.fetchLibraryWithGamesByNamePattern(playerId, "Nonexistent Game"))
                .thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(GameNotFoundException.class, () -> {
            sut.markGameAsFavourite(command);
        });

        assertEquals("Game not found with name: Nonexistent Game", exception.getMessage());
    }

    @Test
    void markGameAsFavourite_GameAlreadyMarked_ShouldThrowException() {
        // Arrange
        GameCommand command = new GameCommand(playerId, gameName, gameId.gameId());
        Library library = TestLibraryData.createLibraryWithChessMaster();

        // Simulate the game is already marked as favorite
        library.findGameById(gameId).markAsFavorite();

        when(libraryLoadPort.fetchLibraryWithGamesByNamePattern(playerId, gameName))
                .thenReturn(library);

        // Act & Assert
        Exception exception = assertThrows(GameAlreadyMarkedAsFavoriteException.class, () -> {
            sut.markGameAsFavourite(command);
        });

        String expectedMessage = "Game is already marked as favorite";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void givePlayerAnAchievement_AchievementAlreadyEarned_ShouldThrowException() {
        // Arrange
        EarnAchievementCommand command = new EarnAchievementCommand(
                playerId,
                gameName,
                gameId.gameId(),
                "First Blood"
        );

        Library library = TestLibraryData.createLibraryWithChessMaster();

        // Simulate the player already earned the achievement
        library.findGameById(gameId).getAchievementList().stream()
                .filter(a -> a.getAchievementName().equals("First Blood"))
                .forEach(Achievement::markAsAchieved);

        when(libraryLoadPort.fetchLibraryWithGamesByNamePattern(playerId, gameName))
                .thenReturn(library);

        // Act & Assert
        Exception exception = assertThrows(AchievementAlreadyEarnedException.class, () -> {
            sut.givePlayerAnAchievement(command);
        });

        String expectedMessage = "Achievement already earned: First Blood";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}