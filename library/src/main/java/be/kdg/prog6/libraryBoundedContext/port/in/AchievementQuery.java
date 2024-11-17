package be.kdg.prog6.libraryBoundedContext.port.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementQuery {

    private String achievementName;
    private String achievementDescription;
    private String imageUrl;
    private boolean achieved;


    public AchievementQuery(String achievementName, String achievementDescription, String imageUrl, boolean achieved) {
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.imageUrl = imageUrl;
        this.achieved = achieved;
    }

    public AchievementQuery () {

    }
}
