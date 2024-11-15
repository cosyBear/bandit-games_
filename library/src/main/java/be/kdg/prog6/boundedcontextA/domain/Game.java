package be.kdg.prog6.boundedcontextA.domain;

import be.kdg.prog6.boundedcontextA.domain.id.GameId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class Game {
    private GameId gameId;

    private String gameName;

    private GameType gameType;

    List<Achievement> achievementList;

    private boolean favourite;

    private String imageUrl;

    public Game(){
        achievementList = new ArrayList<>();
    }

    public Game(GameId gameId, String gameName, GameType gameType, List<Achievement> achievementList, String imageUrl) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameType = gameType;
        this.achievementList = achievementList;
        this.imageUrl = imageUrl;
    }

    public void markAsFavorite()
    {
        this.favourite = true;
    }
    public boolean isGameMarkedAsFavorite(){
        return this.favourite;
    }

    public String updateGameType(GameType newGameType){
        this.gameType = newGameType;
        return "game Type Updated";
    }
    public GameType retrieveGameType(){
        return gameType;
    }
    public String givePlayerAnAchievement(Achievement achievement){
        achievementList.stream()
                .filter(item -> item.getAchievementName().equalsIgnoreCase(achievement.getAchievementName()))
                        .findFirst()
                                .ifPresent(Achievement::markAsAchieved);
        return "Achievement marked as achieved";
    }

    public String addAchievement(Achievement achievement){
        this.achievementList.add(achievement);
        return "achievement Added";
    }
}
