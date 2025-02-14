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
     * @throws ChattyInvalidInputException If the command format is incorrect or unsupported.
     */
    public static Command parse(String command) throws ChattyInvalidInputException {
        if (command.startsWith("bye")) {
            return new ExitCommand();
        }
        if (command.startsWith("list")) {
            return new ListCommand();
        }
        if (command.startsWith("find")) {
            String[] parts = command.split(" ");
            if (parts.length != 2) {
                throw new ChattyInvalidInputException("only one keyword is allowed");
            } else {
                return new FindCommand(parts[1]);
            }
        }
        if (command.startsWith("delete")) {
            String[] commandParts = command.split(" ");
            int taskId = Integer.parseInt(commandParts[1]);
            return new DeleteCommand(taskId);
        }
        if (command.startsWith("mark")) {
            String[] commandParts = command.split(" ");
            if (commandParts.length != 2) {
                throw new ChattyInvalidInputException("Wrong \"mark\" chatty.command format");
            } else {
                int taskId = Integer.parseInt(commandParts[1]);
                return new MarkCommand(taskId);
            }
        }
        if (command.startsWith("unmark")) {
            String[] commandParts = command.split(" ");
            if (commandParts.length != 2) {
                throw new ChattyInvalidInputException("Wrong \"mark\" chatty.command format");
            } else {
                int taskId = Integer.parseInt(commandParts[1]);
                return new UnmarkCommand(taskId);
            }
        }
        if (command.startsWith("todo")) {
            String description = command.substring(4).trim();
            if (description.isEmpty()) {
                throw new ChattyInvalidInputException("Please add an description after \"todo\" separated by a space");
            } else {
                return new TodoCommand(description);
            }
        }
        if (command.startsWith("event")) {
            String eventDetails = command.substring(5).trim();
            if (eventDetails.isEmpty()) {
                throw new ChattyInvalidInputException("Please add details of an event: "
                        + "i.e.\"event <description> /from <start> /to <end>\"");
            } else {
                String[] eventParts = eventDetails.split("/from");
                for (String part : eventParts) {
                    if (part.isEmpty()) {
                        throw new ChattyInvalidInputException("Please follow correct event chatty.command format: "
                                + "i.e.\"event <description> /from <start> /to <end>\"");
                    }
                }
                String description = eventParts[0];
                String[] durationParts = eventParts[1].split("/to");
                for (String part : durationParts) {
                    if (part.trim().isEmpty()) {
                        throw new ChattyInvalidInputException("Please follow correct event chatty.command format: "
                                + "i.e.\"event <description> /from <start> /to <end>\"");
                    }
                }
                String start = durationParts[0].trim();
                String end = durationParts[1].trim();
                return new EventCommand(description, start, end);
            }
        }
        if (command.startsWith("deadline")) {
            String deadlineDetails = command.substring(8).trim();
            if (deadlineDetails.isEmpty()) {
                throw new ChattyInvalidInputException("Please add details of deadline, "
                        + "i.e.\"deadline <description> /by <dd/mm/yyyy HHmm>\"");
            } else {
                String[] details = deadlineDetails.split("/by");
                if (details.length != 2) {
                    throw new ChattyInvalidInputException("Please input correct format of deadline command:"
                            + "\n\"deadline <description> /by <dd/mm/yyyy HHmm>\"");
                } else {
                    try {
                        String deadlineDescription = details[0].trim();
                        String dueString = details[1].trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime deadline = LocalDateTime.parse(dueString, formatter);
                        return new DeadlineCommand(deadlineDescription, deadline);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please enter a valid deadline format dd/MM/yyyy HHmm");
                    }
                }
            }
        }
        throw new ChattyInvalidInputException(command);
    }
}
