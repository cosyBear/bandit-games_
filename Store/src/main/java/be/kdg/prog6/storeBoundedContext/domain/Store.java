package be.kdg.prog6.storeBoundedContext.domain;

import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {


    private Set<Game> availableGames = new HashSet<>();

    public void removeGame(UUID gameId) {
        Game gameToRemove = availableGames.stream()
                .filter(game -> game.getGameId().id().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + gameId));
        availableGames.remove(gameToRemove);
    }


    public void addGame(Game game) {
        if (!availableGames.add(game)) {
            throw new IllegalStateException("Game with ID " + game.getGameId() + " already exists in the store.");
        }
    }


    public Set<Game> listAllGames() {
        return new HashSet<>(availableGames);
    }

    public void purchaseGame(Game game) {
        if (containsGame(game.getGameId())) {
            throw new IllegalStateException("Game with ID " + game.getGameId() + " already exists in the library.");
        }
        this.addGame(game);
    }

    public boolean containsGame(GameId gameId) {
        return availableGames.stream().anyMatch(game -> game.getGameId().equals(gameId));
    }
}