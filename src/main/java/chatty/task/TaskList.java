package chatty.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chatty.exception.ChattyTaskNotFoundException;

/**
 * Represents a list of tasks in the chatty application.
 * <p>
 * This class provides methods for managing tasks, including adding tasks, marking/unmarking tasks, deleting tasks,
 * and converting the task list to a CSV format. It also provides methods for accessing and displaying the list of
 * tasks.
 * </p>
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructs a new empty task list.
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with a given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Gets the task at the specified index in the list.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public Task getTask(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > tasks.size()) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            assert this.tasks.get(index - 1) != null : "Task should exit in the list";
            return tasks.get(index - 1);
        }
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The 1-based index of the task to delete.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public void delete(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > tasks.size()) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            assert tasks.get(index - 1) != null : "task should exist in the list";
            tasks.remove(index - 1);
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add.
     * @return True if the task was added successfully.
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index The 1-based index of the task to mark as completed.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public void mark(int index) throws ChattyTaskNotFoundException {
        getTask(index).markDone();
    }

    /**
     * Unmarks the task at the specified index as not completed.
     *
     * @param index The 1-based index of the task to unmark as completed.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public void unmark(int index) throws ChattyTaskNotFoundException {
        getTask(index).unmarkDone();
    }

    /**
     * Converts the task list to a CSV format.
     * <p>
     * The format will be an array of task strings, where each string represents a task in CSV format.
     * </p>
     *
     * @return An array of strings representing the tasks in CSV format.
     */
    public String[] toCsv() {
        return tasks.stream()
                .map(Task::toCsv)
                .toArray(String[]::new);
    }

    /**
     * Searches for tasks that contain the specified keyword in their names.
     * <p>
     * This method iterates through the task list and checks if each task's name contains the provided keyword
     * (case-insensitive). If a task's name contains the keyword, it is added to a new {@link TaskList} which is then
     * returned.
     * </p>
     *
     * @param keyword The keyword to search for in the task names.
     * @return A {@link TaskList} containing all tasks whose names contain the keyword. If no tasks match, an empty
     *         task list is returned.
     */
    public TaskList tasksContain(String keyword) {
        TaskList taskList = new TaskList();
        if (!this.tasks.isEmpty()) {
            for (Task task : this.tasks) {
                if (task.contains(keyword)) {
                    taskList.add(task);
                }
            }
        }
        return taskList;
    }

    /**
     * Returns a string representation of the task list.
     * <p>
     * If there are no tasks, a message indicating that no tasks are present is returned. Otherwise,
     * the method returns the list of tasks, with each task being prefixed with its index in the list.
     * </p>
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "";
        }
        return tasks.stream()
                .map(task -> String.format("%d. %s", tasks.indexOf(task) + 1, task))
                .collect(Collectors.joining("\n"));
    }
}
