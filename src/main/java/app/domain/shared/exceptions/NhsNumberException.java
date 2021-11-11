package app.domain.shared.exceptions;

public class NhsNumberException extends IllegalArgumentException{

    public NhsNumberException(String message){
        super(message);
    }
}
