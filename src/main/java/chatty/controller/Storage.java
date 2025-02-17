package chatty.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.TaskList;
import chatty.task.Todo;

/**
 * The Storage class handles loading and saving tasks to/from a CSV file.
 * <p>
 * This class is responsible for managing task persistence by interacting with a specified file for storage.
 * It provides methods to load tasks from an existing CSV file or create a new task list if the file is not found,
 * and to save the current task list back into the file.
 * </p>
 */
public class Storage {
    private String filePath = "./data/tasks.csv";

    /**
     * Constructs a Storage object with a specified file path for task storage.
     *
     * @param filePath The path to the file where tasks will be loaded from or saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified CSV file into a TaskList object.
     * <p>
     * If the file does not exist, a new TaskList is returned.
     * Each line in the CSV file is parsed to determine the type of task (Todo, Deadline, Event),
     * and appropriate task objects are created and added to the TaskList.
     * </p>
     *
     * @return A TaskList object containing all tasks loaded from the CSV file.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No existing tasks found. Creating a new local csv for storage");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) { // Ignore empty lines
                    try {
                        char taskType = line.charAt(0);
                        switch (taskType) {
                        case 'T':
                            tasks.add(Todo.fromCsv(line));
                            break;
                        case 'D':
                            tasks.add(Deadline.fromCsv(line));
                            break;
                        case 'E':
                            tasks.add(Event.fromCsv(line));
                            break;
                        default:
                            System.out.println("Skipping unknown chatty.task type: " + line);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping corrupted line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current TaskList into a CSV file.
     * <p>
     * This method converts each task in the TaskList into its CSV representation and writes it to the specified file.
     * If the parent directories of the file path do not exist, they are created.
     * </p>
     *
     * @param tasks The TaskList object containing the tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String[] taskStrings = tasks.toCsv();
            for (String taskString : taskStrings) {
                writer.write(taskString);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}

