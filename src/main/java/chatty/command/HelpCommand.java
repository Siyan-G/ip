package chatty.command;

import chatty.controller.Storage;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.TaskList;
import chatty.ui.Ui;

public class HelpCommand extends Command {

    /**
     * @param tasks   The task list to be modified.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save or load data.
     * @return
     * @throws ChattyTaskNotFoundException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        return "";
    }
}
