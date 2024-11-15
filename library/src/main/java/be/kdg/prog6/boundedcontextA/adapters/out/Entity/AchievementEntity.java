package be.kdg.prog6.boundedcontextA.adapters.out.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class AchievementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID achievementId;

    private String achievementName;

    private String achievementDescription;

    private String imageUrl;

    private boolean achieved;
    public AchievementEntity(){

    }

    public AchievementEntity(UUID achievementId, String achievementName, String achievementDescription, String imageUrl, boolean achieved) {
        this.achievementId = achievementId;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.imageUrl = imageUrl;
        this.achieved = achieved;
    }
}
