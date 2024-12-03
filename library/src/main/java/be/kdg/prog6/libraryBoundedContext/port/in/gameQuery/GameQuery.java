package be.kdg.prog6.libraryBoundedContext.port.in.gameQuery;
import be.kdg.prog6.libraryBoundedContext.domain.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameQuery {

    private String gameName;
    private String description;

    private GameType gameType;

    List<AchievementQuery> achievementList;

    private boolean favourite;

    private String imageUrl;
    private String backgroundImageUrl;

    public GameQuery(UUID id, String gameName, String description, GameType gameType, List<AchievementQuery> achievementQueryList, boolean favourite, String imageUrl, String backgroundImageUrl) {

        this.gameName = gameName;
        this.description = description;
        this.gameType = gameType;
        this.achievementList = achievementQueryList;
        this.favourite = favourite;
        this.imageUrl = imageUrl;
        this.backgroundImageUrl = backgroundImageUrl;


    }
}
