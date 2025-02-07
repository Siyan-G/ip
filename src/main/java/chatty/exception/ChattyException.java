package chatty.exception;

/**
 * Represents a custom exception in the chatty application.
 * <p>
 * This exception class is used to handle general errors in the chatty application.
 * It extends the {@link Exception} class and provides a custom string representation
 * for the exception.
 * </p>
 */
public class ChattyException extends Exception {

    /**
     * Returns a custom string representation of the exception.
     * <p>
     * The default message "Oopsies!" is returned when this exception is thrown.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return "Oopsies !";
    }
}
