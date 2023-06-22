package by.bsuir.restkeeper.domain.exception;

public class InvalidPasswordException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Message
     */
    public InvalidPasswordException(final String message) {
        super(message);
    }

}
