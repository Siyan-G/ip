package chatty.task;

/**
 * Represents a Todo task in the chatty application.
 * <p>
 * This class models a task of type "Todo", which has a description and a completion status.
 * It provides methods to convert a Todo task from and to CSV format, as well as to display the task in a
 * user-friendly string format.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * The task is initially marked as not completed.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isCompleted Whether the task is completed.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Converts a CSV string into a Todo task.
     * The format of the CSV string is expected to be:
     * <ul>
     *     <li>T: Type of task (Todo)</li>
     *     <li>Completion status (1 for completed, 0 for not completed)</li>
     *     <li>Task description</li>
     * </ul>
     *
     * @param line The CSV string representing the Todo task.
     * @return A new Todo task object.
     * @throws IllegalArgumentException If the CSV string is not properly formatted.
     */
    public static Todo fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Corrupted data: " + line);
        }
        boolean isCompleted = parts[1].equals("1");
        String description = parts[2];
        return new Todo(description, isCompleted);
    }

    /**
     * Converts the Todo task to a CSV format string.
     * The format of the string is:
     * <ul>
     *     <li>T: Type of task (Todo)</li>
     *     <li>Completion status (1 for completed, 0 for not completed)</li>
     *     <li>Task description</li>
     * </ul>
     *
     * @return A CSV string representing the Todo task.
     */
    public String toCsv() {
        return String.format("T,%d,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName());
    }

    /**
     * Returns a string representation of the Todo task.
     * The string format is:
     * <ul>
     *     <li>[T] - Represents the Todo task type</li>
     *     <li>Completion status (X for completed, blank for not completed)</li>
     *     <li>Task description</li>
     * </ul>
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

