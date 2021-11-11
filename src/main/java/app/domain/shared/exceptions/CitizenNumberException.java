package app.domain.shared.exceptions;

public class CitizenNumberException extends IllegalArgumentException{

    public CitizenNumberException(String message){
        super(message);
    }
}
