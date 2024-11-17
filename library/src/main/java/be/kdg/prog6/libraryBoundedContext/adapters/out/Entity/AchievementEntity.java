package be.kdg.prog6.libraryBoundedContext.adapters.out.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(catalog = "library")
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
