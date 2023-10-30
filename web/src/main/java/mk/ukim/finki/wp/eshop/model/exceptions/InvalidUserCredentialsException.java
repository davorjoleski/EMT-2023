package mk.ukim.finki.wp.eshop.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException() {
        super("Invalid User Credential Exception");
    }
}
