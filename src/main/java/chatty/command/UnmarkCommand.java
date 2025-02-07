package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;

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
        ui.sendMessage(String.format("Marked chatty.task %d %s as incompleted", taskId, unmarkedTask));
    }
}
