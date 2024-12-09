package be.kdg.prog6.storeBoundedContext.domain;

import be.kdg.prog6.storeBoundedContext.domain.id.GameId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private GameId gameId;
    private String gameName;
    private GameType gameType;
    List<Achievement> achievementList = new ArrayList<>();

    private String imageUrl;

    private String backgroundImageUrl;
    private String description;
    private double price;
    private double rating;
    private SystemRequirements systemRequirements;

    public Game(GameId gameId, String gameName, GameType gameType, String imageUrl, String backgroundImageUrl, String description, double price, double rating, SystemRequirements systemRequirements ) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameType = gameType;
        this.achievementList = new ArrayList<>();
        this.imageUrl = imageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.systemRequirements = systemRequirements;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId != null && gameId.equals(game.gameId);
    }

    @Override
    public int hashCode() {
        return gameId != null ? gameId.hashCode() : 0;
    }

}
