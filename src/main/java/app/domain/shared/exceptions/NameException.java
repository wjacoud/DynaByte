package app.domain.shared.exceptions;

public class NameException extends IllegalArgumentException{

    public NameException(String message){
        super(message);
    }
}
