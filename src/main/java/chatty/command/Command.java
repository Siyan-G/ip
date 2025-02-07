package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.TaskList;

public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }


    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException;
}
