package command;

import controller.Storage;
import controller.Ui;
import task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = tasks.toString();
        ui.sendMessage(response);
    }


}
