package by.bsuir.restkeeper.domain.exception;

public class MailException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Message
     */
    public MailException(final String message) {
        super(message);
    }

}
