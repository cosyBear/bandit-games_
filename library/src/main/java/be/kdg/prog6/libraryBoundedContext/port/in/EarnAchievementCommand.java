package be.kdg.prog6.libraryBoundedContext.port.in;

import be.kdg.prog6.libraryBoundedContext.domain.id.PlayerId;

import java.util.UUID;

public record  EarnAchievementCommand(PlayerId playerId, String gameName , UUID gameId , String AchievementName){
}
