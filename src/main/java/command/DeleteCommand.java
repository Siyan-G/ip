package command;

import controller.Storage;
import controller.Ui;
import exception.ChattyTaskNotFoundException;
import task.Task;
import task.TaskList;

import java.util.List;

public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.delete(taskIndex);
        storage.saveTasks(tasks);
        ui.sendMessage(String.format("Task %d %s has been deleted", taskIndex, deletedTask));
    }
}
