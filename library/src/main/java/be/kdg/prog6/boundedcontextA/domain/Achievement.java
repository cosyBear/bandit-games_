package be.kdg.prog6.boundedcontextA.domain;

import be.kdg.prog6.boundedcontextA.domain.id.AchievementId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Achievement {
    private AchievementId achievementId;
    private String achievementName;
    private String achievementDescription;
    private String imageUrl;
    private boolean achieved;

    public Achievement() {

    }

    public Achievement(AchievementId achievementId, String achievementName, String achievementDescription, String imageUrl, boolean achieved) {
        this.achievementId = achievementId;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.imageUrl = imageUrl;
        this.achieved = achieved;
    }

    public void markAsAchieved() {
        this.achieved = true;
    }

    public String updateImageUrl(String url) {
        this.imageUrl = url;
        return "the url is updated";
    }

}

