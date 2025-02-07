package chatty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the chatty application.
 * <p>
 * This class extends the {@link Task} class and includes a specific deadline time for the task.
 * It provides functionality to manage and format deadline tasks, including serialization and deserialization.
 * </p>
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with a specified name and deadline.
     *
     * @param name     The name or description of the deadline task.
     * @param deadline The deadline date and time for the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructs a new Deadline task with a specified name, completion status, and deadline.
     *
     * @param completed Indicates whether the task is completed.
     * @param name      The name or description of the deadline task.
     * @param deadline  The deadline date and time for the task.
     */
    public Deadline(boolean completed, String name, LocalDateTime deadline) {
        super(name, completed);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline task from a CSV string representation.
     * <p>
     * The expected CSV format is: "D,[completed status],[task name],[deadline in ISO format]".
     * </p>
     *
     * @param line The CSV string representing the deadline task.
     * @return A new {@link Deadline} object created from the CSV data.
     * @throws IllegalArgumentException If the CSV format is incorrect.
     */
    public static Deadline fromCsv(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime deadline = LocalDateTime.parse(parts[3], formatter);
        return new Deadline(parts[1].equals("1"), parts[2], deadline);
    }

    /**
     * Returns a CSV string representation of the deadline task.
     * <p>
     * The format is: "D,[completed status],[task name],[deadline in ISO format]".
     * </p>
     *
     * @return A CSV string representing the deadline task.
     */
    public String toCsv() {
        return String.format("D,%d,%s,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName(),
                this.deadline);
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The format is: "[D] [task status] [task name] (by: [formatted deadline])".
     * </p>
     *
     * @return A string representing the deadline task, including its status and deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String deadlineString = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }
}

