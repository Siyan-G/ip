package chatty.command;

import chatty.controller.Storage;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 * <p>
 * This class is used to mark a specific task in the TaskList as completed based on the provided task ID.
 * It also saves the updated task list to storage and provides feedback to the user through the Ui component.
 * </p>
 */
public class MarkCommand extends Command {
    private final int taskId;

    /**
     * Constructs a MarkCommand with a specified task ID to mark the corresponding task as completed.
     *
     * @param taskId The ID of the task to be marked as completed.
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the command to mark the specified task as completed and save the updated task list to storage.
     * Provides feedback to the user on the result of the operation.
     *
     * @param tasks The TaskList where the task will be marked as completed.
     * @param ui The UI to communicate feedback to the user.
     * @param storage The storage responsible for saving tasks.
     * @throws ChattyTaskNotFoundException If the task with the given ID is not found in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        tasks.mark(taskId);
        storage.saveTasks(tasks);
        Task markedTask = tasks.getTask(taskId);
        return ui.getMessage(String.format("Marked task %d %s as completed", taskId, markedTask));
    }
}
