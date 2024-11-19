package be.kdg.prog6.libraryBoundedContext.adapters.out.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(catalog = "library")
@Data   @AllArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID gameId;

    private String gameName;

    @Enumerated(EnumType.STRING)
    private GameTypeEntity gameType;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = false)
    @JoinColumn(name = "game_id")
    private List<AchievementEntity> achievementList = new ArrayList<>();

    private String imageUrl;

    private boolean favourite;

    @ManyToOne()
    @JoinColumn(name = "library_id")
    private LibraryEntity library;

    public GameEntity(){

    }


    public GameEntity(UUID uuid, String gameName, GameTypeEntity gameTypeEntity, List<AchievementEntity> achievementEntities, String imageUrl, boolean favourite) {
        this.gameId = uuid;
        this.gameName = gameName;
        this.gameType = gameTypeEntity;
        this.achievementList = achievementEntities;
        this.imageUrl = imageUrl;
        this.favourite = favourite;
    }
}
