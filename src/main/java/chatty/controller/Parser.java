package chatty.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatty.command.Command;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.EventCommand;
import chatty.command.ExitCommand;
import chatty.command.FindCommand;
import chatty.command.HelpCommand;
import chatty.command.ListCommand;
import chatty.command.MarkCommand;
import chatty.command.TodoCommand;
import chatty.command.UnmarkCommand;
import chatty.exception.ChattyInvalidCommandException;
import chatty.exception.ChattyInvalidCommandFormatException;

/**
 * The Parser class is responsible for parsing user input commands and returning the corresponding Command object.
 * <p>
 * This class processes a given string command, identifies the type of command, and constructs the appropriate Command
 * object. It also validates the format of the user input and throws exceptions for invalid inputs. Supported commands
 * include tasks management (such as adding, deleting, marking, and unmarking tasks), as well as event and deadline
 * management.
 * </p>
 */
public class Parser {

    /**
     * Parses the given command and returns the corresponding Command object.
     *
     * @param command The user input command string to be parsed.
     * @return The appropriate Command object based on the user input.
     * @throws ChattyInvalidCommandException If the command format is incorrect or unsupported.
     */
    public static Command parse(String command)
            throws ChattyInvalidCommandException, ChattyInvalidCommandFormatException {
        if (command.startsWith("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("list")) {
            return new ListCommand();
        } else if (command.startsWith("find")) {
            return parseFindCommand(command);
        } else if (command.startsWith("delete")) {
            return parseDeleteCommand(command);
        } else if (command.startsWith("mark")) {
            return parseMarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return parseUnmarkCommand(command);
        } else if (command.startsWith("todo")) {
            return parseTodoCommand(command);
        } else if (command.startsWith("event")) {
            return parseEventCommand(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineCommand(command);
        }
        if (command.startsWith("help")) {
            return new HelpCommand();
        }
        throw new ChattyInvalidCommandException(command);
    }

    /**
     * Parses the "find" command.
     *
     * @param command The user input command string.
     * @return The corresponding FindCommand.
     * @throws ChattyInvalidCommandFormatException If the command format is incorrect.
     */
    private static Command parseFindCommand(String command) throws ChattyInvalidCommandFormatException {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.FIND);
        }
        return new FindCommand(parts[1]);
    }

    /**
     * Parses the "delete" command.
     *
     * @param command The user input command string.
     * @return The corresponding DeleteCommand.
     */
    private static Command parseDeleteCommand(String command) throws ChattyInvalidCommandFormatException {
        String[] commandParts = command.split(" ");
        if (commandParts.length != 2) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.DELETE);
        }
        int taskId = Integer.parseInt(commandParts[1]);
        return new DeleteCommand(taskId);
    }

    /**
     * Parses the "mark" command.
     *
     * @param command The user input command string.
     * @return The corresponding MarkCommand.
     * @throws ChattyInvalidCommandFormatException If the command format is incorrect.
     */
    private static Command parseMarkCommand(String command) throws ChattyInvalidCommandFormatException {
        String[] commandParts = command.split(" ");
        if (commandParts.length != 2) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.MARK);
        }
        int taskId = Integer.parseInt(commandParts[1]);
        return new MarkCommand(taskId);
    }

    /**
     * Parses the "unmark" command.
     *
     * @param command The user input command string.
     * @return The corresponding UnmarkCommand.
     * @throws ChattyInvalidCommandFormatException If the command format is incorrect.
     */
    private static Command parseUnmarkCommand(String command) throws ChattyInvalidCommandFormatException {
        String[] commandParts = command.split(" ");
        if (commandParts.length != 2) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.UNMARK);
        }
        int taskId = Integer.parseInt(commandParts[1]);
        return new UnmarkCommand(taskId);
    }

    /**
     * Parses the "todo" command.
     *
     * @param command The user input command string.
     * @return The corresponding TodoCommand.
     * @throws ChattyInvalidCommandFormatException If no description is provided.
     */
    private static Command parseTodoCommand(String command) throws ChattyInvalidCommandFormatException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.TODO);
        }
        return new TodoCommand(description);
    }

    /**
     * Parses the "event" command.
     *
     * @param command The user input command string.
     * @return The corresponding EventCommand.
     * @throws ChattyInvalidCommandFormatException If the event format is incorrect.
     */
    private static Command parseEventCommand(String command) throws ChattyInvalidCommandFormatException {
        String eventDetails = command.substring(5).trim();
        if (eventDetails.isEmpty()) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.EVENT);
        }
        String[] eventParts = eventDetails.split("/from");
        if (eventParts.length != 2) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.EVENT);
        }
        for (String part : eventParts) {
            if (part.isEmpty()) {
                throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.EVENT);
            }
        }
        String description = eventParts[0];
        String[] durationParts = eventParts[1].split("/to");
        for (String part : durationParts) {
            if (part.trim().isEmpty()) {
                throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.EVENT);
            }
        }
        String start = durationParts[0].trim();
        String end = durationParts[1].trim();
        return new EventCommand(description, start, end);
    }

    /**
     * Parses the "deadline" command.
     *
     * @param command The user input command string.
     * @return The corresponding DeadlineCommand.
     * @throws ChattyInvalidCommandFormatException If the deadline format is incorrect.
     */
    private static Command parseDeadlineCommand(String command) throws ChattyInvalidCommandFormatException {
        String deadlineDetails = command.substring(8).trim();
        if (deadlineDetails.isEmpty()) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.DEADLINE);
        }
        String[] details = deadlineDetails.split("/by");
        if (details.length != 2) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.DEADLINE);
        }
        try {
            String deadlineDescription = details[0].trim();
            String dueString = details[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(dueString, formatter);
            return new DeadlineCommand(deadlineDescription, deadline);
        } catch (DateTimeParseException e) {
            throw new ChattyInvalidCommandFormatException(ChattyInvalidCommandFormatException.CommandType.DEADLINE);
        }
    }
}
