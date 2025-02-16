package chatty.ui;

/**
 * The {@code Ui} class handles all interactions with the user in the Chatty application.
 * <p>
 * It provides methods to send messages, display introduction and exit messages,
 * show help or error messages, and ensure an interactive user experience.
 * </p>
 */
public class Ui {
    private static final String INTRO_MSG = "Hello Master! I'm Chatty, your ever-ready personal assistant."
            + "How can I help you today?"
            + "\nType \"help\" and click \"Send\" to view the list of command available";
    private static final String EXIT_MSG = "Goodbye Master! See you soon!";
    private static final String HELP_MSG = """
            Here are some commands you can use:
            - todo [task]: Adds a new task
            - deadline [task] /by [dd/mm/yyyy hhmm]: Adds a task with a deadline
            - event [task] /at [date]: Adds an event
            - list: Displays all tasks
            - bye: Exits the application\
            - mark [task number]: to mark a task as completed
            - unmark [task number]: to mark a task as not completed
            - find [keyword]: to find all tasks containing the keyword in the description
            - bye: to exit the application""";

    /**
     * Returns the introduction message to welcome the user.
     *
     * @return The introduction message.
     */
    public String getIntroMsg() {
        return INTRO_MSG;
    }

    /**
     * Returns the exit message when the user leaves the application.
     *
     * @return The exit message.
     */
    public String getExitMsg() {
        return EXIT_MSG;
    }

    /**
     * Returns the provided message as-is.
     *
     * @param msg The message to be sent to the user.
     * @return The same message that was passed as a parameter.
     */
    public String getMessage(String msg) {
        return msg;
    }

    /**
     * Returns a help message containing available commands.
     *
     * @return The help message.
     */
    public String sendHelp() {
        return HELP_MSG;
    }

    /**
     * Returns an error message with the specified error details.
     *
     * @param msg The error details to be displayed.
     * @return The formatted error message.
     */
    public String sendError(String msg) {
        return msg;
    }
}
