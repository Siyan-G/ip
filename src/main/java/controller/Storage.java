package controller;

import task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath = "./data/tasks.csv";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                            System.out.println("Skipping unknown task type: " + line);
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

    public void saveTasks(TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String[] taskStrings = tasks.toCSV();
            for (String taskString : taskStrings) {
                writer.write(taskString);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
