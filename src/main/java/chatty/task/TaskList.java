package chatty.task;

import java.util.ArrayList;
import java.util.List;

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
    private int numOfTasks;

    /**
     * Constructs a new empty task list.
     * Initializes an empty list of tasks and sets the number of tasks to 0.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numOfTasks = 0;
    }

    /**
     * Constructs a task list with a given list of tasks and the number of tasks.
     *
     * @param tasks      The list of tasks.
     * @param numOfTasks The number of tasks in the list.
     */
    public TaskList(ArrayList<Task> tasks, int numOfTasks) {
        this.tasks = tasks;
        this.numOfTasks = numOfTasks;
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
            return tasks.get(index - 1);
        }
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The 1-based index of the task to delete.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public void delete(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > numOfTasks) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            tasks.remove(index - 1);
            numOfTasks--;
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add.
     * @return True if the task was added successfully.
     */
    public boolean add(Task task) {
        this.tasks.add(task);
        numOfTasks++;
        return true;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index The 1-based index of the task to mark as completed.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public void mark(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > numOfTasks) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            tasks.get(index - 1).markDone();
        }
    }

    /**
     * Unmarks the task at the specified index as not completed.
     *
     * @param index The 1-based index of the task to unmark as completed.
     * @throws ChattyTaskNotFoundException If the task at the specified index does not exist.
     */
    public void unmark(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > numOfTasks) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            tasks.get(index - 1).unmarkDone();
        }
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
        String[] taskList = new String[numOfTasks];
        for (int i = 0; i < numOfTasks; i++) {
            taskList[i] = tasks.get(i).toCsv();
        }
        return taskList;
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
     * @throws ChattyTaskNotFoundException If there are no tasks in the list or if none of the tasks contain the
     *         keyword.
     */
    public TaskList tasksContain(String keyword) throws ChattyTaskNotFoundException {
        TaskList taskList = new TaskList();
        if (numOfTasks == 0) {
            return taskList;
        } else {
            for (int i = 0; i < numOfTasks; i++) {
                if (tasks.get(i).contains(keyword)) {
                    taskList.add(tasks.get(i));
                }
            }
            return taskList;
        }
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
        if (numOfTasks == 0) {
            return "No task currently";
        } else {
            StringBuilder sb = new StringBuilder(String.format("You current have %d tasks in the list",
                    this.getNumOfTasks()));
            for (int i = 0; i < numOfTasks; i++) {
                sb.append("\n").append(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            return sb.toString();
        }
    }
}
