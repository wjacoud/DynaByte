package app.domain.shared.exceptions;

public class PhoneNumberException extends IllegalArgumentException{

    public PhoneNumberException(String message){
        super(message);
    }
}
