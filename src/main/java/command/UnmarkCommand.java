package command;

import controller.Storage;
import controller.Ui;
import exception.ChattyTaskNotFoundException;
import task.Task;
import task.TaskList;

public class UnmarkCommand extends Command {
    private final int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        tasks.unmark(taskId);
        storage.saveTasks(tasks);
        Task unmarkedTask = tasks.getTask(taskId);
        ui.sendMessage(String.format("Marked task %d %s as incompleted", taskId, unmarkedTask));
    }
}
