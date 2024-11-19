package be.kdg.prog6.common.events.util;


public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException(String message) {
        super(message);
    }

    public LibraryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
