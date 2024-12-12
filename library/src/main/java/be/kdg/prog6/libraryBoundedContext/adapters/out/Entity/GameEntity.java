package be.kdg.prog6.libraryBoundedContext.adapters.out.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "LibraryGameEntity")
@Table(catalog = "library", name = "game")
@Data
@AllArgsConstructor
@BatchSize(size = 10) // Adjust the batch size as needed
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

    private boolean favourite;

    private String description;


    @ManyToOne()
    @JoinColumn(name = "library_id")
    private LibraryEntity libraryEntity;



    public GameEntity() {

    }


    public GameEntity(UUID uuid, String gameName, GameTypeEntity gameTypeEntity, List<AchievementEntity> achievementEntities, String imageUrl, boolean favourite) {
        this.gameId = uuid;
        this.gameName = gameName;
        this.gameType = gameTypeEntity.toString();
        this.achievementList = achievementEntities;
        this.imageUrl = imageUrl;
        this.favourite = favourite;
    }
}
