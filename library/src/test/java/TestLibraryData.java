import be.kdg.prog6.libraryBoundedContext.domain.Achievement;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.AchievementId;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.LibraryId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

import java.util.List;
import java.util.UUID;

public class TestLibraryData {

    public static Library createLibraryWithChessMaster() {
        // Create Achievement objects for Chess Master
        Achievement achievement1 = new Achievement(
                new AchievementId(UUID.fromString("c7a4f9d3-8e12-4b7c-8a96-7b94e2836c74")),
                "First Blood",
                "Win your first match",
                "https://example.com/images/achievement1.png",
                false
        );

        Achievement achievement2 = new Achievement(
                new AchievementId(UUID.fromString("ea8f1c32-9b4c-4d5e-93a6-2a67f845b1c3")),
                "Grandmaster",
                "Win 10 matches in a row",
                "https://example.com/images/achievement2.png",
                true
        );

        // Create Game object for Chess Master
        Game chessMasterGame = new Game(
                new GameId(UUID.fromString("e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1")),
                "Chess Master",
                GameType.BOARD_GAME,
                List.of(achievement1, achievement2),
                "https://example.com/images/chess.png",
                false
        );

        // Create Library object with Chess Master
        return new Library(
                new LibraryId(UUID.fromString("456e7891-e89b-12d3-a456-426614174000")),
                List.of(chessMasterGame),
                new PlayerId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
        );
    }
}
