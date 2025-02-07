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
