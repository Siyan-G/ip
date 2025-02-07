package chatty;

import chatty.command.Command;
import chatty.controller.Parser;
import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyException;
import chatty.task.*;

/**
 * The main class that runs the Chatty task management application.
 * <p>
 * This class is responsible for initializing the user interface (UI), managing task storage,
 * and interacting with the task list. It runs a loop that continuously accepts user commands,
 * parses them, and executes the corresponding actions until the user decides to exit.
 * </p>
 */
public class Chatty {

    private TaskList taskList; // List of tasks being managed.
    private Storage storage;   // Storage for loading and saving tasks.
    private Ui ui;             // User interface for interacting with the user.

    /**
     * Constructs a new Chatty instance, initializing the UI, storage, and task list.
     * The task list is loaded from the specified CSV file.
     *
     * @param filePath The file path of the CSV file containing saved tasks.
     */
    public Chatty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.loadTasks();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Starts the Chatty application by displaying the introduction message and entering the main command loop.
     * The loop continues until the user exits the application.
     */
    public void run() {
        ui.sendIntroMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(ui, fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (ChattyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Chatty application.
     * <p>
     * This method initializes a new Chatty instance with the path to the tasks CSV file
     * and starts the application by calling the {@link #run()} method.
     * </p>
     *
     * @param args Command line arguments (not used in this version).
     */
    public static void main(String[] args) {
        new Chatty("./data/tasks.csv").run();
    }
}
