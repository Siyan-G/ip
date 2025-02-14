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
import chatty.command.ListCommand;
import chatty.command.MarkCommand;
import chatty.command.TodoCommand;
import chatty.command.UnmarkCommand;
import chatty.exception.ChattyInvalidInputException;

/**
 * The Parser class is responsible for parsing user input commands and returning the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the given command and returns the corresponding Command object.
     *
     * @param command The user input command string to be parsed.
     * @return The appropriate Command object based on the user input.
     * @throws ChattyInvalidInputException If the command format is incorrect or unsupported.
     */
    public static Command parse(String command) throws ChattyInvalidInputException {
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
        throw new ChattyInvalidInputException(command);
    }

    /**
     * Parses the "find" command.
     *
     * @param command The user input command string.
     * @return The corresponding FindCommand.
     * @throws ChattyInvalidInputException If the command format is incorrect.
     */
    private static Command parseFindCommand(String command) throws ChattyInvalidInputException {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            throw new ChattyInvalidInputException("only one keyword is allowed");
        }
        return new FindCommand(parts[1]);
    }

    /**
     * Parses the "delete" command.
     *
     * @param command The user input command string.
     * @return The corresponding DeleteCommand.
     */
    private static Command parseDeleteCommand(String command) {
        String[] commandParts = command.split(" ");
        int taskId = Integer.parseInt(commandParts[1]);
        return new DeleteCommand(taskId);
    }

    /**
     * Parses the "mark" command.
     *
     * @param command The user input command string.
     * @return The corresponding MarkCommand.
     * @throws ChattyInvalidInputException If the command format is incorrect.
     */
    private static Command parseMarkCommand(String command) throws ChattyInvalidInputException {
        String[] commandParts = command.split(" ");
        if (commandParts.length != 2) {
            throw new ChattyInvalidInputException("Wrong \"mark\" chatty.command format");
        }
        int taskId = Integer.parseInt(commandParts[1]);
        return new MarkCommand(taskId);
    }

    /**
     * Parses the "unmark" command.
     *
     * @param command The user input command string.
     * @return The corresponding UnmarkCommand.
     * @throws ChattyInvalidInputException If the command format is incorrect.
     */
    private static Command parseUnmarkCommand(String command) throws ChattyInvalidInputException {
        String[] commandParts = command.split(" ");
        if (commandParts.length != 2) {
            throw new ChattyInvalidInputException("Wrong \"unmark\" chatty.command format");
        }
        int taskId = Integer.parseInt(commandParts[1]);
        return new UnmarkCommand(taskId);
    }

    /**
     * Parses the "todo" command.
     *
     * @param command The user input command string.
     * @return The corresponding TodoCommand.
     * @throws ChattyInvalidInputException If no description is provided.
     */
    private static Command parseTodoCommand(String command) throws ChattyInvalidInputException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new ChattyInvalidInputException("Please add a description after \"todo\" separated by a space");
        }
        return new TodoCommand(description);
    }

    /**
     * Parses the "event" command.
     *
     * @param command The user input command string.
     * @return The corresponding EventCommand.
     * @throws ChattyInvalidInputException If the event format is incorrect.
     */
    private static Command parseEventCommand(String command) throws ChattyInvalidInputException {
        String eventDetails = command.substring(5).trim();
        if (eventDetails.isEmpty()) {
            throw new ChattyInvalidInputException("Please add details of an event: i.e."
                    + "\"event <description> /from <start> /to <end>\"");
        }
        String[] eventParts = eventDetails.split("/from");
        for (String part : eventParts) {
            if (part.isEmpty()) {
                throw new ChattyInvalidInputException("Please follow correct event chatty.command format");
            }
        }
        String description = eventParts[0];
        String[] durationParts = eventParts[1].split("/to");
        for (String part : durationParts) {
            if (part.trim().isEmpty()) {
                throw new ChattyInvalidInputException("Please follow correct event chatty.command format");
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
     * @throws ChattyInvalidInputException If the deadline format is incorrect.
     */
    private static Command parseDeadlineCommand(String command) throws ChattyInvalidInputException {
        String deadlineDetails = command.substring(8).trim();
        if (deadlineDetails.isEmpty()) {
            throw new ChattyInvalidInputException("Please add details of deadline: i.e."
                    + "\"deadline <description> /by <dd/MM/yyyy HHmm>\"");
        }
        String[] details = deadlineDetails.split("/by");
        if (details.length != 2) {
            throw new ChattyInvalidInputException("Please input correct format of deadline command: "
                    + "\"deadline <description> /by <dd/MM/yyyy HHmm>\"");
        }
        try {
            String deadlineDescription = details[0].trim();
            String dueString = details[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(dueString, formatter);
            return new DeadlineCommand(deadlineDescription, deadline);
        } catch (DateTimeParseException e) {
            throw new ChattyInvalidInputException("Please enter a valid deadline format dd/MM/yyyy HHmm");
        }
    }
}
