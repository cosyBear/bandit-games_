package be.kdg.prog6.libraryBoundedContext.domain;

import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import be.kdg.prog6.libraryBoundedContext.domain.id.LibraryId;
import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    private LibraryId libraryId;
    private List<Game> games = new ArrayList<>();
    private PlayerId playerId;

    public Library(LibraryId libraryId, List<Game> games) {
        this.libraryId = libraryId;
        this.games = games;
    }

    public Game toggleFavouriteForGame() {
        Game game = games.getFirst();
        game.toggleFavorite();
        return game;
    }

    public boolean containsGame(GameId gameId) {
        return games.stream().anyMatch(game -> game.getGameId().equals(gameId));
    }

    public Game findGameById(GameId gameId) {
        return games.stream()
                .filter(game -> game.getGameId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + gameId));
    }

    public List<Game> getFavoriteGames() {
        return games.stream()
                .filter(Game::isGameMarkedAsFavorite)
                .toList();
    }

    public Game givePlayerAnAchievement(GameId gameId, String achievementName) {
        Game game = games.stream()
                .filter(g -> g.getGameId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + gameId));
        game.givePlayerAnAchievement(achievementName);

        return game;
    }


    public void addGame(Game game) {
        if (containsGame(game.getGameId())) {
            throw new IllegalStateException("Game with ID " + game.getGameId() + " already exists in the library.");
        }
        games.add(game);
    }

    public void removeGame(Game game) {
        if (!containsGame(game.getGameId())) {
            throw new IllegalArgumentException("Game with ID " + game.getGameId() + " does not exist in the library.");
        }
        games.remove(game);
    }


}
