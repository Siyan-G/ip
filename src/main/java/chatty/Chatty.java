package chatty;

import chatty.command.Command;
import chatty.controller.Parser;
import chatty.controller.Storage;
import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * The {@code Chatty} class serves as the main entry point for the Chatty task management application.
 * <p>
 * It initializes the user interface (UI), manages task storage, and interacts with the task list.
 * The application runs by continuously accepting user commands, parsing them, and executing the
 * corresponding actions until the user chooses to exit.
 * </p>
 */
public class Chatty {

    private TaskList taskList; // List of tasks being managed.
    private Storage storage; // Storage for loading and saving tasks.
    private Ui ui; // User interface for interacting with the user.

    /**
     * Constructs a new {@code Chatty} instance, initializing the UI, storage, and task list.
     * <p>
     * The task list is loaded from the specified CSV file. If loading fails, an empty task list is initialized.
     * </p>
     *
     * @param filePath The file path of the CSV file containing saved tasks.
     */
    public Chatty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.loadTasks();
        } catch (Exception e) {
            ui.sendError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns the introductory message for the Chatty application.
     * <p>
     * This method retrieves a predefined welcome message from the UI to be displayed when the application starts.
     * </p>
     *
     * @return A string containing the welcome message.
     */
    public String intro() {
        return ui.getIntroMsg();
    }

    /**
     * Processes a user command and returns the appropriate response.
     * <p>
     * This method parses the input command, executes the corresponding action,
     * and returns the response message. If an error occurs, an error message is returned instead.
     * </p>
     *
     * @param input The user command as a string.
     * @return The response message after executing the command.
     * @throws ChattyException If the command results in an error.
     */
    public String getResponse(String input) throws ChattyException {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (ChattyException e) {
            return ui.sendError(e.getMessage());
        }
    }
}
