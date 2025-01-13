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
    private String imageUrl;
    private String backgroundImageUrl;
    private String description;
    private double price;
    private double rating;
    List<Achievement> achievementList = new ArrayList<>();
    private String domainUrl;

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
