package be.kdg.prog6.libraryBoundedContext.port.in;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import lombok.Getter;
import lombok.Setter;

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
