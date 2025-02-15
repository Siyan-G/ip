package chatty.command;

import chatty.controller.Storage;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Represents a command that provides help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the HelpCommand to display a list of available commands to the user.
     *
     * @param tasks   The task list (not used in this command but required by the method signature).
     * @param ui      The user interface to interact with the user.
     * @param storage The storage system (not used in this command but required by the method signature).
     * @return A string containing the help message.
     * @throws ChattyTaskNotFoundException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        return ui.sendHelp();
    }
}
