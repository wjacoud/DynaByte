package app.domain.shared.exceptions;

public class MatrixInvalidLines extends IllegalArgumentException{

    public MatrixInvalidLines(String message){
        super(message);
    }
}
