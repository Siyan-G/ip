package command;

import controller.Storage;
import controller.Ui;
import exception.ChattyTaskNotFoundException;
import task.TaskList;

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
