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
