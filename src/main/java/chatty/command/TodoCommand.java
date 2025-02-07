package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.Todo;

/**
 * Represents a command to add a new "To-Do" task to the task list.
 * <p>
 * This class is used to create a Todo task with a description and add it to the TaskList.
 * It also saves the updated task list to storage and provides feedback to the user through the Ui component.
 * </p>
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with a specified description for the new To-Do task.
     *
     * @param description A brief description of the To-Do task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add the new To-Do task to the task list and save the updated list to storage.
     * Provides feedback to the user on the result of the operation.
     *
     * @param tasks The TaskList to which the new To-Do task will be added.
     * @param ui The UI to communicate feedback to the user.
     * @param storage The storage responsible for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.sendMessage(String.format("Task %s added to list.\nYou now have %d tasks tracked",
                description,
                tasks.getNumOfTasks()));
    }
}
