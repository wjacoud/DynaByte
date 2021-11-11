package app.domain.shared.exceptions;

public class LevelException extends IllegalArgumentException{

    public LevelException(String message){
        super(message);
    }
}
