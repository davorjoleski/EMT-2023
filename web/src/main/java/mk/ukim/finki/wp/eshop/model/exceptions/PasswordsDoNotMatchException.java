package mk.ukim.finki.wp.eshop.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("Wrong password entered.");
    }
}
