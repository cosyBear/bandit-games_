package be.kdg.prog6.storeBoundedContext.adapters.out.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity(name = "StoreGameEntity") // Unique entity name
@Table(catalog = "store", name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID gameId;
    private String gameName;
    private String gameType;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "gameEntity")
    private List<AchievementEntity> achievementList = new ArrayList<>();
    private String imageUrl;
    private String backgroundImageUrl;
    private String description;
    private double price;
    private double rating;


    @Embedded
    private SystemRequirementsEntity systemRequirements;



    @Override
    public int hashCode() {
        return gameId != null ? gameId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameEntity that = (GameEntity) obj;
        return gameId != null && gameId.equals(that.gameId);
    }
}
