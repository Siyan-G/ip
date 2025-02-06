import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDateTime deadline;

    public Deadlines(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadlines(boolean completed, String name, LocalDateTime deadline) {
        super(name, completed);
        this.deadline = deadline;
    }

    public static Deadlines fromCsv(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime deadline = LocalDateTime.parse(parts[3], formatter);
        return new Deadlines(parts[1].equals("1"), parts[2] , deadline);
    }

    public String toCsv() {
        return String.format("D,%d,%s,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName(),
                this.deadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String deadlineString = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }
}
