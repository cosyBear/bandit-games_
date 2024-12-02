package be.kdg.prog6.libraryBoundedContext.domain;

import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Store {

    private final List<Game> availableGames = new ArrayList<>();
    //add game reviews attributes to each game


    public void addGame(Game game) {
        if (availableGames.stream().anyMatch(g -> g.getGameId().equals(game.getGameId()))) {
            throw new IllegalStateException("Game with ID " + game.getGameId() + " already exists in the store.");
        }
        availableGames.add(game);
    }

    public Game getGameById(GameId gameId) {
        return availableGames.stream()
                .filter(game -> game.getGameId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + gameId));
    }

    public List<Game> listAllGames() {
        return new ArrayList<>(availableGames);
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
