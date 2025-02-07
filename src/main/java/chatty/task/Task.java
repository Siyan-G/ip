package chatty.task;

public class Task {
    private String taskName;
    private boolean isCompleted = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.isCompleted = done;
    }

    public void markDone() {
        this.isCompleted = true;
    }

    public void unmarkDone() {
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

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

    public String toCsv() {
        return String.format("%s,%s", this.taskName, this.isCompleted() ? 1 : 0);
    }

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
