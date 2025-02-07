package command;

import controller.Storage;
import controller.Ui;
import task.Deadline;
import task.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

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
