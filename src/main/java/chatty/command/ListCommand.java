package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = tasks.toString();
        ui.sendMessage(response);
    }


}
