package be.kdg.prog6.common.events;

import java.util.UUID;

public record GameAchievementEvent (UUID playerId, String gameName  , String AchievementName ) {
        }
