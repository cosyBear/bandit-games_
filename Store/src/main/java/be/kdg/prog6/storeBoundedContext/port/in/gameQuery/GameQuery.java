package be.kdg.prog6.storeBoundedContext.port.in.gameQuery;

import be.kdg.prog6.storeBoundedContext.domain.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameQuery {

    private String gameName;
    private String description;

    private GameType gameType;

    List<AchievementQuery> achievementList;

    private String imageUrl;
    private String backgroundImageUrl;
    private double price;
    private SystemRequirementsQuery systemRequirements;


}
