package be.kdg.prog6.storeBoundedContext.domain;

import be.kdg.prog6.storeBoundedContext.domain.id.AchievementId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Achievement {
    private AchievementId achievementId;
    private String achievementName;
    private String achievementDescription;
    private String imageUrl;



    public Achievement(String achievementName, String achievementDescription, String imageUrl) {
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.imageUrl = imageUrl;
    }

    public Achievement(AchievementId achievementId, String achievementName, String achievementDescription, String imageUrl) {
        this.achievementId = achievementId;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.imageUrl = imageUrl;
    }


}

