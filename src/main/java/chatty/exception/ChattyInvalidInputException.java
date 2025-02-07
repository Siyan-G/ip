package chatty.exception;

/**
 * Represents an exception that is thrown when an invalid input is detected for a chatty command.
 * <p>
 * This exception extends the {@link ChattyException} class and is used to indicate that an invalid input
 * was passed for a specific command in the chatty application. It provides details about the command that caused the error.
 * </p>
 */
public class ChattyInvalidInputException extends ChattyException {

    private final String command;

    /**
     * Constructs a new ChattyInvalidInputException with the specified command.
     *
     * @param command The command that caused the invalid input error.
     */
    public ChattyInvalidInputException(String command) {
        this.command = command;
    }

    /**
     * Returns a detailed message describing the invalid input error.
     * <p>
     * The message includes the command that caused the exception.
     * </p>
     *
     * @return A string message that indicates the invalid input for the command.
     */
    @Override
    public String getMessage() {
        return String.format("I could not execute this command: %s", command);
    }

    /**
     * Returns a custom string representation of the exception.
     * <p>
     * The string representation includes the message with the command that caused the exception.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return String.format("I could not execute this command: %s", command);
    }
}

