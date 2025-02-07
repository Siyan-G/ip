package chatty.exception;

/**
 * Represents an exception that is thrown when an invalid argument is detected for a chatty command.
 * <p>
 * This exception extends the {@link ChattyException} class and is used to indicate that an invalid argument
 * was passed for a specific command in the chatty application. It provides details about the command type
 * that caused the error.
 * </p>
 */
public class ChattyInvalidArgumentException extends ChattyException {

    private final String commandType;

    /**
     * Constructs a new ChattyInvalidArgumentException with the specified command type.
     *
     * @param commandType The type of the command that caused the invalid argument error.
     */
    public ChattyInvalidArgumentException(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Returns a detailed message describing the invalid argument error.
     * <p>
     * The message includes the command type that caused the exception.
     * </p>
     *
     * @return A string message that indicates the invalid argument for the command.
     */
    @Override
    public String getMessage() {
        return String.format(" Invalid argument detected for chatty.command: %s", commandType);
    }

    /**
     * Returns a custom string representation of the exception.
     * <p>
     * The string representation includes the message with the command type that caused the exception.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return String.format("ChattyInvalidArgumentException: Invalid argument for command %s", commandType);
    }
}

