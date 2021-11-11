package app.domain.shared.exceptions;

public class EmailException extends IllegalArgumentException{

    public EmailException(String message){
        super(message);
    }
}
