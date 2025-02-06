public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isCompleted){
        super(description, isCompleted);
    }

    public static Todo fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Corrupted data: " + line);
        }
        boolean isCompleted = parts[1].equals("1");
        String description = parts[2];
        return new Todo(description, isCompleted);
    }

    public String toCsv() {
        return String.format("T,%d,%s",
                super.isCompleted() ? 1 : 0,
                super.getTaskName());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
