package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.TaskList;

/**
 * Represents a command that allows the user to find tasks based on a keyword.
 * <p>
 * The {@link FindCommand} class is responsible for searching the task list for tasks containing the specified keyword.
 * If no tasks are found, a message is displayed indicating that no matching tasks were found. Otherwise, a list of
 * tasks that match the keyword is shown to the user.
 * </p>
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a {@link FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks containing the specified keyword.
     * <p>
     * If no tasks are found with the keyword, a message is sent to the user indicating that no tasks were found.
     * If tasks are found, a message is sent to the user with the number of matching tasks and their details.
     * </p>
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface to communicate results to the user.
     * @param storage The storage used to save or load tasks (not used in this command, but included for consistency).
     * @throws ChattyTaskNotFoundException If an error occurs while searching for tasks (e.g., invalid task access).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChattyTaskNotFoundException {
        TaskList relevantTasks = tasks.tasksContain(keyword);
        if (relevantTasks == null || relevantTasks.getNumOfTasks() == 0) {
            ui.sendMessage("No task found with keyword " + keyword);
        } else {
            ui.sendMessage(String.format("Found %d task(s) with keyword: %s%s",
                    relevantTasks.getNumOfTasks(),
                    this.keyword,
                    relevantTasks.toString()));
        }
    }
}
