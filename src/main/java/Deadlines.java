public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadlines(boolean completed, String name, String deadline) {
        super(name, completed);
        this.deadline = deadline;
    }

    public static Deadlines fromCsv(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
        return new Deadlines(parts[1].equals("1"), parts[2] , parts[3]);
    }

    public String toCsv() {
        return String.format("D,%d,%s,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName(),
                this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
