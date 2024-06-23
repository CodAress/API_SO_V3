package upc.edu.NerdyNestAPI.utils.exception;

public class ValidationException extends RuntimeException {
    public ValidationException() {
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
