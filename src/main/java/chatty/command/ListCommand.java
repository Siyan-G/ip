package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * This class is used to retrieve all tasks from the TaskList and display them to the user.
 * It converts the task list into a string representation and sends it to the user through the Ui component.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display all tasks in the task list.
     * The list of tasks is converted to a string and sent as a message to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @param ui The UI to communicate the list of tasks to the user.
     * @param storage The storage, which is not modified in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage(String.format("You currently have %d tasks in the list%s",
                tasks.getNumOfTasks(),
                tasks));
    }
}
