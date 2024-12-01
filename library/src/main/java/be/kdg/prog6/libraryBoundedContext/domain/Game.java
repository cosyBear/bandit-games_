package be.kdg.prog6.libraryBoundedContext.domain;

import be.kdg.prog6.common.events.util.AchievementAlreadyEarnedException;
import be.kdg.prog6.common.events.util.AchievementNotFoundException;
import be.kdg.prog6.common.events.util.GameAlreadyMarkedAsFavoriteException;
import be.kdg.prog6.libraryBoundedContext.domain.id.GameId;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private GameId gameId;
    private String gameName;
    private String description;

    private GameType gameType;

    List<Achievement> achievementList = new ArrayList<>();

    private boolean favourite;

    private String imageUrl;
    private String backgroundImageUrl;

    public Game(GameId gameId, String gameName, GameType gameType, List<Achievement> achievementList, String imageUrl, boolean favourite) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameType = gameType;
        this.achievementList = achievementList;
        this.imageUrl = imageUrl;
        this.favourite = favourite;
    }

    public Game(String gameName, GameType gameType, List<Achievement> achievementList, String imageUrl, boolean favourite) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.achievementList = achievementList;
        this.imageUrl = imageUrl;
        this.favourite = favourite;
    }

    public void toggleFavorite() {
        this.favourite = !this.favourite;
    }

    public boolean isGameMarkedAsFavorite() {
        return this.favourite;
    }


    public String updateGameType(GameType newGameType) {
        this.gameType = newGameType;
        return "game Type Updated";
    }

    public GameType retrieveGameType() {
        return gameType;
    }


    public void givePlayerAnAchievement(String achievementName) {
        achievementList.stream()
                .filter(a -> a.getAchievementName().equalsIgnoreCase(achievementName))
                .findFirst()
                .ifPresentOrElse(
                        achievement -> {
                            if (achievement.isAchieved()) {
                                throw new AchievementAlreadyEarnedException("Achievement already earned: " + achievementName);
                            }
                            achievement.markAsAchieved();
                        },
                        () -> { throw new AchievementNotFoundException("Achievement not found: " + achievementName); }
                );
    }

    public void addAchievement(Achievement achievement) {
        this.achievementList.add(achievement);
    }
}
