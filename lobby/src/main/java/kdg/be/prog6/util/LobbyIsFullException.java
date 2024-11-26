package kdg.be.prog6.util;

public class LobbyIsFullException extends RuntimeException{
    public LobbyIsFullException(String message) {
        super(message);
    }
}
