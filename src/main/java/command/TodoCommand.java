package command;

import controller.Storage;
import controller.Ui;
import task.Task;
import task.TaskList;
import task.Todo;

public class TodoCommand extends Command{
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

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
