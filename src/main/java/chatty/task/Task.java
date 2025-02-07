package chatty.task;

/**
 * Represents a general task in the chatty application.
 * <p>
 * This class provides basic functionalities to track a task's name and completion status.
 * It also includes methods for marking the task as done, unmarking it, and serializing the task to and from CSV format.
 * </p>
 */
public class Task {
    private String taskName;
    private boolean isCompleted = false;

    /**
     * Constructs a new Task with a specified name.
     * The task is initially marked as not completed.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Constructs a new Task with a specified name and completion status.
     *
     * @param taskName  The name or description of the task.
     * @param done      Indicates whether the task is completed.
     */
    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.isCompleted = done;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmarkDone() {
        this.isCompleted = false;
    }

    /**
     * Gets the name or description of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Checks whether the task is completed.
     *
     * @return True if the task is completed, otherwise false.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The format is: "[X] task name" if the task is completed, or "[ ] task name" if not.
     * </p>
     *
     * @return A string representing the task's completion status and name.
     */
    @Override
    public String toString() {
        String done = this.isCompleted ? "X" : " ";
        return "[" + done + "] " + this.taskName;
    }

    /**
     * Checks if the task name contains the specified keyword (case-insensitive).
     * <p>
     * This method checks if the {@link Task}'s name contains the provided keyword, ignoring case differences.
     * If the keyword exists within the task name, the method returns {@code true}, otherwise {@code false}.
     * </p>
     *
     * @param keyWord The keyword to search for within the task name.
     * @return {@code true} if the task name contains the keyword (case-insensitive), otherwise {@code false}.
     */
    public boolean contains(String keyWord) {
        return this.taskName.contains(keyWord.toLowerCase());
    }

    /**
     * Returns a CSV string representation of the task.
     * <p>
     * The format is: "task name,completed status".
     * </p>
     *
     * @return A CSV string representing the task.
     */
    public String toCsv() {
        return String.format("%s,%s", this.taskName, this.isCompleted() ? 1 : 0);
    }

    /**
     * Creates a Task object from a CSV string representation.
     * <p>
     * The expected CSV format is: "task name,completed status" where the completed status is either 0 (not completed) or 1 (completed).
     * </p>
     *
     * @param csv The CSV string representing the task.
     * @return A new {@link Task} object created from the CSV data.
     * @throws IllegalArgumentException If the CSV format is incorrect.
     */
    public static Task fromCsv(String csv) throws IllegalArgumentException {
        String[] tokens = csv.split(",");
        if (tokens.length != 2) {
            throw new IllegalArgumentException();
        }
        String taskName = tokens[0];
        boolean isCompleted = Boolean.parseBoolean(tokens[1]);
        return new Task(taskName, isCompleted);
    }
}
