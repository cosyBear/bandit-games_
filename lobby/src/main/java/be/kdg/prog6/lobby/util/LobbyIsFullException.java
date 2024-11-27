package be.kdg.prog6.lobby.util;

public class LobbyIsFullException extends RuntimeException{
    public LobbyIsFullException(String message) {
        super(message);
    }
}
