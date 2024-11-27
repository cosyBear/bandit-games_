package be.kdg.prog6.common.events.util;

public class AchievementAlreadyEarnedException extends RuntimeException {
    public AchievementAlreadyEarnedException(String message) {
        super(message);
    }
}
