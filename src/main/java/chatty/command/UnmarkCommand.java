package chatty.command;

import chatty.controller.Storage;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Represents a command to unmark a task as incomplete.
 * <p>
 * This class is used to unmark a specific task in the TaskList as incomplete based on the provided task ID.
 * It also saves the updated task list to storage and provides feedback to the user through the Ui component.
 * </p>
 */
public class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Constructs an UnmarkCommand with a specified task ID to unmark the corresponding task as incomplete.
     *
     * @param taskId The ID of the task to be unmarked as incomplete.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the command to unmark the specified task as incomplete and save the updated task list to storage.
     * Provides feedback to the user on the result of the operation.
     *
     * @param tasks The TaskList where the task will be unmarked as incomplete.
     * @param ui The UI to communicate feedback to the user.
     * @param storage The storage responsible for saving tasks.
     * @throws ChattyTaskNotFoundException If the task with the given ID is not found in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        tasks.unmark(taskId);
        storage.saveTasks(tasks);
        Task unmarkedTask = tasks.getTask(taskId);
        return ui.getMessage(String.format("Uh oh!! task %d: %s, is incompleted.", taskId, unmarkedTask));
    }
}

