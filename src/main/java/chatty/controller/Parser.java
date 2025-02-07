package chatty.controller;

import chatty.command.*;
import chatty.exception.ChattyInvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(Ui ui, String command) throws ChattyInvalidInputException {
        if (command.startsWith("bye")) {
            return new ExitCommand();
        }
        if (command.startsWith("list")) {
            return new ListCommand();
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
                throw new ChattyInvalidInputException("Please add details of an event: i.e.\"event <description> /from <start> /to <end>\"");
            } else {
                String[] eventParts = eventDetails.split("/from");
                for (String part : eventParts) {
                    if (part.isEmpty()) {
                        throw new ChattyInvalidInputException("Please follow correct event chatty.command format: i.e.\"event <description> /from <start> /to <end>\"");
                    }
                }
                String description = eventParts[0];
                String[] durationParts = eventParts[1].split("/to");
                for (String part : durationParts) {
                    if (part.trim().isEmpty()) {
                        throw new ChattyInvalidInputException("Please follow correct event chatty.command format: i.e.\"event <description> /from <start> /to <end>\"");
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
                throw new ChattyInvalidInputException("Please add details of deadline, i.e.\"deadline <description> /by <dd/mm/yyyy HHmm>\"");
            } else {
                String[] details = deadlineDetails.split("/by");
                if (details.length != 2) {
                    throw new ChattyInvalidInputException("Please input correct format of deadline chatty.command:\n\"deadline <description> /by <dd/mm/yyyy HHmm>");
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
        throw new ChattyInvalidInputException("Command not found");
    }
}
