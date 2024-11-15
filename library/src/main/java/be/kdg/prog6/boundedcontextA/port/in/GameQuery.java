package be.kdg.prog6.boundedcontextA.port.in;

import be.kdg.prog6.boundedcontextA.domain.Achievement;
import be.kdg.prog6.boundedcontextA.domain.GameType;
import be.kdg.prog6.boundedcontextA.domain.id.GameId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class GameQuery {

    private String gameName;

    private GameType gameType;

    List<AchievementQuery> achievementList;

    private boolean favourite;

    private String imageUrl;

    public GameQuery() {
    }

    public GameQuery(String gameName, GameType gameType, List<AchievementQuery> achievementList, boolean favourite, String imageUrl) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.achievementList = achievementList;
        this.favourite = favourite;
        this.imageUrl = imageUrl;
    }
}
