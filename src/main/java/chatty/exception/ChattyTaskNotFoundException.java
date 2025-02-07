package chatty.exception;

public class ChattyTaskNotFoundException extends ChattyException {

    private int taskId;

    public ChattyTaskNotFoundException(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getMessage() {
        return String.format(" Sorry! chatty.task %d could not be found", taskId);
    }

    @Override
    public String toString() {
        return String.format(" Sorry! chatty.task %d could not be found", taskId);
    }
}
