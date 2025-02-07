package chatty.exception;

public class ChattyInvalidArgumentException extends ChattyException{

    private final String commandType;

    public ChattyInvalidArgumentException(String commandType) {
        this.commandType = commandType;
    }

    @Override
    public String getMessage() {
        return String.format(" Invalid argument detected for chatty.command: %s", commandType);
    }

    @Override
    public String toString() {
        return String.format(" Invalid argument detected for chatty.command: %s", commandType);
    }
}
