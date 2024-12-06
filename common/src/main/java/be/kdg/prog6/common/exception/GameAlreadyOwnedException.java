package be.kdg.prog6.common.exception;

public class GameAlreadyOwnedException extends RuntimeException{
    public GameAlreadyOwnedException(String message){
        super(message);
    }
}
