package command;

import controller.Storage;
import controller.Ui;
import task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendExitMsg();
    }
}
