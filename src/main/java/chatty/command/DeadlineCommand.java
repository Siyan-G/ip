package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.task.Deadline;
import chatty.task.TaskList;
import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline to a list of tasks.
 * <p>
 * This class is used to create a Deadline object with a description and a deadline time,
 * and then add it to the TaskList. It also saves the updated list of tasks to storage
 * and provides feedback to the user through the Ui component.
 * </p>
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    /**
     * Constructs a DeadlineCommand with a specified description and deadline.
     *
     * @param description A brief description of the deadline task.
     * @param deadline The date and time when the deadline is set.
     */
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the command to add the deadline task to the task list and save it to storage.
     * Provides feedback to the user on the result of the operation.
     *
     * @param tasks The TaskList where the Deadline will be added.
     * @param ui The UI to communicate feedback to the user.
     * @param storage The storage responsible for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.deadline);
        tasks.add(deadline);
        storage.saveTasks(tasks);
        ui.sendMessage(String.format("Deadline %s added to the list\nNow you have %d tasks in the list",
                deadline,
                tasks.getNumOfTasks()));
    }
}

