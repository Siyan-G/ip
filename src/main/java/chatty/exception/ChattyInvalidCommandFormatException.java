package chatty.exception;

/**
 * Represents an exception that is thrown when an invalid argument is detected for a chatty command.
 * <p>
 * This exception extends the {@link ChattyException} class and is used to indicate that an invalid argument
 * was passed for a specific command in the chatty application. It provides details about the command type
 * that caused the error, such as the correct format and an example of valid usage.
 * </p>
 */
public class ChattyInvalidCommandFormatException extends ChattyException {

    /**
     * Enum representing different command types in the chatty application.
     * <p>
     * Each command type includes the correct format of the command and an example usage of the command.
     * </p>
     */
    public enum CommandType {
        TODO,
        EVENT,
        DEADLINE,
        MARK,
        UNMARK,
        LIST,
        BYE,
        DELETE,
        FIND;

        private String correctFormat;
        private String example;

        // Static block to initialize the enum constants with their default values
        static {
            TODO.correctFormat = "todo <task description>";
            TODO.example = "todo buy groceries";

            EVENT.correctFormat = "event <event description> /from <date/time> /to <date/time>";
            EVENT.example = "event department meeting /from tuesday 2pm /to 6pm";

            DEADLINE.correctFormat = "deadline <task description> /by <dd/mm/yyyy hhmm>";
            DEADLINE.example = "deadline Submit report /by 01/03/2025 1800";

            MARK.correctFormat = "mark <task number>";
            MARK.example = "mark 2";

            UNMARK.correctFormat = "unmark <task number>";
            UNMARK.example = "unmark 2";

            LIST.correctFormat = "list";
            LIST.example = "list";

            BYE.correctFormat = "bye";
            BYE.example = "bye";

            DELETE.correctFormat = "delete <task number>";
            DELETE.example = "delete 3";

            FIND.correctFormat = "find <keyword>";
            FIND.example = "find groceries";
        }

        /**
         * Returns the correct format for the command type.
         *
         * @return The correct format string for the command.
         */
        public String correctCommandFormat() {
            return correctFormat;
        }

        /**
         * Returns an example usage of the command type.
         *
         * @return The example usage string for the command.
         */
        public String exampleUsage() {
            return example;
        }
    }

    private final CommandType commandType;

    /**
     * Constructs a new {@code ChattyInvalidCommandFormatException} with the specified command type.
     * <p>
     * This constructor is used to create an exception with information about the command that caused
     * the invalid argument error.
     * </p>
     *
     * @param commandType The type of the command that caused the invalid argument error.
     */
    public ChattyInvalidCommandFormatException(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Returns a detailed message describing the invalid argument error.
     * <p>
     * The message includes the command type that caused the exception, the correct format for the command,
     * and an example usage of the command.
     * </p>
     *
     * @return A string message that indicates the invalid command argument, with the correct format and example.
     */
    @Override
    public String getMessage() {
        return String.format("OOPSIES! Command: \"%s\" is incorrect.\nCorrect format: %s.\nExample: %s",
                commandType, commandType.correctCommandFormat(), commandType.exampleUsage());
    }

    /**
     * Returns a custom string representation of the exception.
     * <p>
     * This string representation includes the detailed message with the command type that caused the exception.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
