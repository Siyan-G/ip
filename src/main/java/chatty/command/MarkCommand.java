package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;

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
        ui.sendMessage(String.format("Marked chatty.task %d %s as completed", taskId, markedTask));
    }
}
