package be.kdg.prog6.boundedcontextA.adapters.out.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(catalog = "library")
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

    public GameEntity() {
    }

    public GameEntity(UUID gameId, String gameName, GameTypeEntity gameType, List<AchievementEntity> achievementList, String imageUrl, boolean favourite) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameType = gameType;
        this.achievementList = achievementList;
        this.imageUrl = imageUrl;
        this.favourite = favourite;
    }
}
