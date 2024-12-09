package be.kdg.prog6.storeBoundedContext.adapters.out.Entity;



import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity(name = "StoreAchievementEntity")
@Table(name = "store_achievement" , catalog = "store")
public class AchievementEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID achievementId;


    private String achievementName;

    private String achievementDescription;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity gameEntity;

    public AchievementEntity(){

    }
    @Override
    public int hashCode() {
        return achievementId != null ? achievementId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AchievementEntity that = (AchievementEntity) obj;
        return achievementId != null && achievementId.equals(that.achievementId);
    }


}
