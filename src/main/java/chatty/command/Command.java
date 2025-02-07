package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.TaskList;

/**
 * Represents an abstract command that can be executed.
 * Each command may modify the task list, interact with the user, and update storage.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructs a non-exit command.
     * The command does not terminate the program.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Constructs a command with the specified exit status.
     *
     * @param isExit {@code true} if the command should terminate the program, {@code false} otherwise.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return {@code true} if the command terminates the program, {@code false} otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to be modified.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save or load data.
     * @throws ChattyTaskNotFoundException If the specified task is not found.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException;
}
