package be.kdg.prog6.storeBoundedContext.port.in.gameQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementQuery {

    private String achievementName;
    private String achievementDescription;
    private String imageUrl;


    public AchievementQuery(String achievementName, String achievementDescription, String imageUrl) {
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.imageUrl = imageUrl;
    }

    public AchievementQuery () {

    }
}
