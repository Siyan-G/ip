package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.task.TaskList;

/**
 * Represents a command to exit the application.
 * <p>
 * This class is used to terminate the application by sending an exit message
 * through the Ui component. It does not modify the task list or storage.
 * </p>
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand that signals the termination of the application.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command to exit the application by sending an exit message to the user.
     *
     * @param tasks The TaskList, which is not modified in this command.
     * @param ui The UI to communicate the exit message to the user.
     * @param storage The storage, which is not modified in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendExitMsg();
    }
}

