import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskStorageManager {
    private static final String DIRECTORY = "./data"; // Folder for tasks
    private static final String FILE_PATH = DIRECTORY + "/tasks.csv";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

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
                            tasks.add(Deadlines.fromCsv(line));
                            break;
                        case 'E':
                            tasks.add(Events.fromCsv(line));
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

    public static void saveTasks(List<Task> tasks) {
        try {
            Files.createDirectories(Paths.get(DIRECTORY));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : tasks) {
                    writer.write(task.toCsv());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
