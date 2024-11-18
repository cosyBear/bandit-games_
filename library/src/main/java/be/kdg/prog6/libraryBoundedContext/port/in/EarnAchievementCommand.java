package be.kdg.prog6.libraryBoundedContext.port.in;

import java.util.UUID;

public record  EarnAchievementCommand(UUID playerId, String gameName , String AchievementName){
}
