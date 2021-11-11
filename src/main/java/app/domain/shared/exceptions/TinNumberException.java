package app.domain.shared.exceptions;

public class TinNumberException extends IllegalArgumentException{

    public TinNumberException(String message){
        super(message);
    }
}
