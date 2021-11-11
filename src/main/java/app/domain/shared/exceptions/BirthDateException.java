package app.domain.shared.exceptions;

public class BirthDateException extends IllegalArgumentException{

    public BirthDateException(String message){
        super(message);
    }
}
