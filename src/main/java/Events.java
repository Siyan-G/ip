public class Events extends Task{

    private String start;
    private String end;

    public Events(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Events(String name, boolean completed, String start, String end) {
        super(name, completed);
        this.start = start;
        this.end = end;
    }

    public static Events fromCsv(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Corrupted data: " + line);
        }
        boolean isCompleted = parts[1].equals("1");
        String description = parts[2];
        String start = parts[3];
        String end = parts[4];
        return new Events(description, isCompleted, start, end);
    }

    public String toCsv() {
        return String.format("E,%d,%s,%s,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName(),
                this.start,
                this.end);
    }

    @Override
    public String toString() {
        return "[E]" +
                super.toString() +
                " (from: " + start + " to " + end + ")";
    }
}
