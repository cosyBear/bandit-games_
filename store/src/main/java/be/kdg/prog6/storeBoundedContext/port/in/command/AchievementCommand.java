package be.kdg.prog6.storeBoundedContext.port.in.command;

public record AchievementCommand (String achievementName,
                                 String achievementDescription,
                                 String imageUrl){
}
