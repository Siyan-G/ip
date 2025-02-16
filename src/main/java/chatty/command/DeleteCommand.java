package chatty.command;

import chatty.controller.Storage;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.ui.Ui;
/**
 * Represents a command to delete a task from the task list.
 * <p>
 * This class allows the deletion of a task from the TaskList based on the specified index.
 * It also updates the storage by saving the modified list of tasks and provides feedback
 * to the user through the Ui component.
 * </p>
 */
public class DeleteCommand extends Command {

    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with a specified task index.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete the specified task from the task list and save the updated list to storage.
     * Provides feedback to the user on the result of the operation.
     *
     * @param tasks The TaskList from which the task will be deleted.
     * @param ui The UI to communicate feedback to the user.
     * @param storage The storage responsible for saving tasks.
     * @throws ChattyTaskNotFoundException If the task with the given index is not found in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.delete(taskIndex);
        storage.saveTasks(tasks);
        return ui.getMessage(String.format("Yikes! Task %d: %s, has been deleted.", taskIndex, deletedTask));
    }
}

