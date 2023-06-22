package by.bsuir.restkeeper.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Message
     */
    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

}
