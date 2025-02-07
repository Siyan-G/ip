package chatty.exception;

public class ChattyInvalidInputException extends ChattyException{

    private final String command;

    public ChattyInvalidInputException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("I could not execute this command: %s", command);
    }

    @Override
    public String toString() {
        return String.format("I could not execute this command: %s", command);
    }
}
