package app.domain.shared.exceptions;

public class DateException extends IllegalArgumentException{

    public DateException(String message){
        super(message);
    }
}
