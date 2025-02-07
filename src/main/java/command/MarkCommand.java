package command;

import controller.Storage;
import controller.Ui;
import exception.ChattyTaskNotFoundException;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        tasks.mark(taskId);
        storage.saveTasks(tasks);
        Task markedTask = tasks.getTask(taskId);
        ui.sendMessage(String.format("Marked task %d %s as completed", taskId, markedTask));
    }
}
