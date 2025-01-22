public class Task {
    private String taskName;
    private boolean done = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String toString() {
        String done = this.done ? "X" : " ";
        return "[" + done + "] " + this.taskName;
    }
}
