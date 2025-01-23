import exception.ChattyException;
import exception.ChattyInvalidArgumentException;
import exception.ChattyInvalidInputException;
import exception.ChattyTaskNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Chatty {
    private static final String linebreak = "___________________________________________________________________";
    private static final String introMsg = "\n\tHello Master! I'm Chatty, your ever-ready personal assistant.\n\tHow can I help you today?";
    private static final String exitMsg = "\n\tGoodbye Master! See you soon!";
    private ArrayList<Task> taskList = new ArrayList<>();
    private Integer taskCounter = 0;

    private void Response(String message) {
        System.out.println("Chatty:" + message);
        System.out.println(linebreak);
    }

    public void Intro() {
        System.out.println(linebreak);
        this.Response(introMsg);
    }

    public void Exit() {
        this.Response(exitMsg);
    }

    public void handleCommand(String command) {
        try{
            // handle list command
            if (command.equalsIgnoreCase("list")) {
                this.printTaskList();
            }
            else if (command.startsWith("delete")) {
                this.deleteTask(command);
            }
            // handle mark and unmark task
            else if (command.startsWith("mark") || command.startsWith("unmark")) {
                this.setTaskStatus(command);
            }
            // handle task creation
            else if (command.startsWith("todo") || command.startsWith("event") || command.startsWith("deadline")) {
                this.handleTaskCreation(command);
            }
            else {
                throw new ChattyInvalidInputException(command);
            }
        } catch (ChattyException e) {
            this.Response(e.getMessage());
        }

    }

    public void deleteTask(String command) throws ChattyTaskNotFoundException, ChattyInvalidArgumentException {
        String[] commandParts = command.split(" ");
        if (commandParts.length == 2) {
            int taskId = Integer.parseInt(commandParts[1].trim());
            if (taskId > taskCounter || taskId < 1) {
                throw new ChattyTaskNotFoundException(taskId);
            } else {
                taskList.remove(taskId - 1);
                taskCounter--;
                this.Response(String.format("\n\tRoger! I've removed task %d from the list:\n\t%s\n\t" +
                        "Now you have %d tasks in the list.", taskId,taskList.get(taskId - 1), taskCounter));
            }
        } else {
            throw new ChattyInvalidArgumentException(command);
        }
    }

    public void printTaskList() {
        if (taskCounter == 0) {
            this.Response(" Your list of tasks is empty!");
        }
        else {
            StringBuilder response = new StringBuilder(String.format("\n\tYou have %d tasks in the list:", taskCounter));
            for (int i = 0; i < taskCounter; i++) {
                response.append(String.format("\n\t%d. %s", i + 1, taskList.get(i)));
            }
            this.Response(response.toString());
        }
    }

    public void setTaskStatus(String command) throws ChattyTaskNotFoundException {
        String[] commandParts = command.split(" ");
        if (commandParts.length == 2) {
            int taskId = Integer.parseInt(commandParts[1].trim());
            if (taskId > taskCounter || taskId < 1) {
                throw new ChattyTaskNotFoundException(taskId);
            }
            StringBuilder response = new StringBuilder();
            if (commandParts[0].equals("mark")) {
                taskList.get(taskId - 1).markDone();
                response.append("\n\tNice! I've marked this task as done:\n");
                response.append(String.format("\t%s", taskList.get(taskId - 1)));
            } else {
                taskList.get(taskId - 1).unmarkDone();
                response.append("\n\tOkay! I've marked this task as not done yet:\n");
                response.append(String.format("\t%s", taskList.get(taskId - 1)));
            }
            this.Response(response.toString());
        }
    }

    public void handleTaskCreation(String command) throws ChattyInvalidArgumentException {
        StringBuilder response = new StringBuilder();
        if (command.startsWith("todo")) {
            String taskName = command.substring(4).trim();
            if (taskName.isEmpty()) {
                throw new ChattyInvalidArgumentException("todo");
            }
            taskList.add(new Todo(taskName));
            taskCounter++;
            response.append("\n\tI've added this task to the list:\n");
            response.append(String.format("\t%s\n", taskList.get(taskCounter - 1)));
            response.append(String.format("\tNow you have %d tasks in the list.", taskCounter));
            this.Response(response.toString());
        }
        if (command.startsWith("event")) {
            String taskString = command.substring(5).trim();
            String[] taskParts = taskString.split("/");
            if (taskParts.length == 3) {
                String eventName = taskParts[0].trim();
                String startTime = taskParts[1].substring(4).trim();
                String endTime = taskParts[2].substring(3).trim();
                taskList.add(new Events(eventName, startTime, endTime));
                taskCounter++;
                response.append("\n\tI've added this task to the list:\n");
                response.append(String.format("\t%s\n", taskList.get(taskCounter - 1)));
                response.append(String.format("\tNow you have %d tasks in the list.", taskCounter));
                this.Response(response.toString());
            }
            else {
                throw new ChattyInvalidArgumentException("event");
            }
        }
        if (command.startsWith("deadline")) {
            String taskString = command.substring(8).trim();
            String[] taskParts = taskString.split("/");
            if (taskParts.length == 2) {
                String deadlineName = taskParts[0].trim();
                String due = taskParts[1].substring(3).trim();
                taskList.add(new Deadlines(deadlineName, due));
                taskCounter++;
                response.append("\n\tI've added this task to the list:\n");
                response.append(String.format("\t%s\n", taskList.get(taskCounter - 1)));
                response.append(String.format("\tNow you have %d tasks in the list.", taskCounter));
                this.Response(response.toString());
            }
            else {
                throw new ChattyInvalidArgumentException("deadline");
            }
        }

    }

    public static void main(String[] args) {

        Chatty chatty = new Chatty();
        Scanner scanner = new Scanner(System.in);
        chatty.Intro();

        while (true) {
            System.out.print("Master: ");
            String userInput = scanner.nextLine();
            chatty.handleCommand(userInput);
            if (userInput.equalsIgnoreCase("bye")) {
                chatty.Exit();
                scanner.close();
                break;
            }
        }
    }
}
