package app.domain.shared.exceptions;

public class AddressException extends IllegalArgumentException{

    public AddressException(String message){
        super(message);
    }
}
