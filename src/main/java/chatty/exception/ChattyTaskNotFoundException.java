package chatty.exception;

/**
 * Represents an exception that is thrown when a task could not be found in the chatty application.
 * <p>
 * This exception extends the {@link ChattyException} class and is used to indicate that a specific task
 * could not be found based on its task ID. It provides details about the task ID that caused the error.
 * </p>
 */
public class ChattyTaskNotFoundException extends ChattyException {

    private int taskId;

    /**
     * Constructs a new ChattyTaskNotFoundException with the specified task ID.
     *
     * @param taskId The ID of the task that could not be found.
     */
    public ChattyTaskNotFoundException(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns a detailed message describing the error when a task is not found.
     * <p>
     * The message includes the task ID that could not be located.
     * </p>
     *
     * @return A string message that indicates the task could not be found.
     */
    @Override
    public String getMessage() {
        return String.format(" Sorry! task %d could not be found", taskId);
    }

    /**
     * Returns a custom string representation of the exception.
     * <p>
     * The string representation includes the message with the task ID that caused the exception.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return String.format(" Sorry! task %d could not be found", taskId);
    }
}

