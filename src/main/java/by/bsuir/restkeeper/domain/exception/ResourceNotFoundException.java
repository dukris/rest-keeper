package by.bsuir.restkeeper.domain.exception;

public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }

}
