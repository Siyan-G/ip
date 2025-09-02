package chatty.task;

/**
 * Represents an event task in the chatty application.
 * <p>
 * This class extends the {@link Task} class and includes a start and end time for the event.
 * It provides functionality to manage and format event tasks, including serialization and deserialization.
 * </p>
 */
public class Event extends Task {

    private String start;
    private String end;

    /**
     * Constructs a new Event task with a specified name, start time, and end time.
     *
     * @param name  The name or description of the event.
     * @param start The start time of the event in string format.
     * @param end   The end time of the event in string format.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Event task with a specified name, completion status, start time, and end time.
     *
     * @param name      The name or description of the event.
     * @param completed Indicates whether the task is completed.
     * @param start     The start time of the event in string format.
     * @param end       The end time of the event in string format.
     */
    public Event(String name, boolean completed, String start, String end) {
        super(name, completed);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an Event task from a CSV string representation.
     * <p>
     * The expected CSV format is: "E,[completed status],[task name],[start time],[end time]".
     * </p>
     *
     * @param line The CSV string representing the event task.
     * @return A new {@link Event} object created from the CSV data.
     * @throws IllegalArgumentException If the CSV format is incorrect.
     */
    public static Event fromCsv(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Corrupted data: " + line);
        }
        boolean isCompleted = parts[1].equals("1");
        String description = parts[2];
        String start = parts[3];
        String end = parts[4];
        return new Event(description, isCompleted, start, end);
    }

    /**
     * Returns a CSV string representation of the event task.
     * <p>
     * The format is: "E,[completed status],[task name],[start time],[end time]".
     * </p>
     *
     * @return A CSV string representing the event task.
     */
    public String toCsv() {
        return String.format("E,%d,%s,%s,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName(),
                this.start,
                this.end);
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     * The format is: "[E] [task status] [task name] (from: [start time] to [end time])".
     * </p>
     *
     * @return A string representing the event task, including its status, start, and end times.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(from: " + start + " to " + end + ")";
    }
}

