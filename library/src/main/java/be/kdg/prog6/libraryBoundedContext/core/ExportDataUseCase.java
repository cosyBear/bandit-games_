package be.kdg.prog6.libraryBoundedContext.core;

import be.kdg.prog6.libraryBoundedContext.domain.Achievement;
import be.kdg.prog6.libraryBoundedContext.domain.Game;
import be.kdg.prog6.libraryBoundedContext.domain.Library;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import be.kdg.prog6.libraryBoundedContext.port.in.command.ExportUserDataCommand;
import be.kdg.prog6.libraryBoundedContext.port.in.game.ExportUserData;
import be.kdg.prog6.libraryBoundedContext.port.out.LibraryLoadPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

@Slf4j
@Service
@AllArgsConstructor
public class ExportDataUseCase implements ExportUserData {
    private final LibraryLoadPort loadPort;


    @Override
    public byte[] exportUserData(ExportUserDataCommand command) {
        Library library = loadPort.fetchLibraryWithAllAvailableGames(command.playerId());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(outputStream)) {

            // Write player details
            writer.println("Player ID, Player Name");
            writer.printf("%s, %s%n", command.playerId().Id(), command.playerName());
            writer.println();

            writer.println("Game ID, Game Name, Description, Favorite, Achievement Name, Achievement Description, Achieved");

            for (Game game : library.getGames()) {
                for (Achievement achievement : game.getAchievementList()) {
                    writer.printf("%s, %s, %s, %s, %s, %s, %s%n",
                            game.getGameId(),
                            game.getGameName(),
                            game.getDescription(),
                            game.isGameMarkedAsFavorite(),
                            achievement.getAchievementName(),
                            achievement.getAchievementDescription(),
                            achievement.isAchieved());
                }
                if (game.getAchievementList().isEmpty()) {
                    writer.printf("%s, %s, %s, %s, , , %n",
                            game.getGameId(),
                            game.getGameName(),
                            game.getDescription(),
                            game.isGameMarkedAsFavorite());
                }
            }

            writer.flush();
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("Failed to export data for player: {}", command.playerId(), e);
            throw new RuntimeException("Error exporting user data", e);
        }
    }
}

