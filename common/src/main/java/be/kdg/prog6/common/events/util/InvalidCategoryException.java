package be.kdg.prog6.common.events.util;

public class InvalidCategoryException extends RuntimeException {

    public InvalidCategoryException(String message) {
        super(message);
    }

    public InvalidCategoryException(String message , Throwable cause) {
        super(message, cause);
    }
}
