import java.util.Scanner;

public class Chatty {
    public static void main(String[] args) {
        String linebreak = "____________________________________________________________";
        String introMsg = "Hello Master! I'm Chatty, your ever-ready personal assistant.\nHow can I help you today?";
        String exitMsg = "Goodbye Master! See you soon!";
        System.out.println(linebreak);
        System.out.println(introMsg);
        System.out.println(linebreak);

        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskCounter = 0;

        while (true) {
            System.out.print("Master: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(exitMsg);
                System.out.println(linebreak);
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                if (taskCounter == 0) {
                    System.out.println("Chatty: your task list is empty.");
                }
                else {
                    System.out.printf("Chatty: your have %d tasks in the list:\n", taskCounter);
                    for (int i = 0; i < taskList.length; i++) {
                        if (taskList[i] == null) {
                            break;
                        }
                        System.out.printf("%d. %s\n", i + 1, taskList[i]);
                    }
                    System.out.println(linebreak);
                }
                continue;
            }

            if (userInput.toLowerCase().startsWith("mark")) {
                int taskNumber = Integer.parseInt(String.valueOf(userInput.charAt(5)));
                if (taskNumber > taskCounter) {
                    System.out.printf("Chatty: task %d does not exist.\n", taskNumber);
                } else {
                    taskList[taskNumber - 1].markDone();
                    System.out.printf("Chatty: I've marked this task done:\n%d: ", taskNumber);
                    System.out.println(taskList[taskNumber - 1]);
                }
                System.out.println(linebreak);
                continue;
            }

            if (userInput.toLowerCase().startsWith("unmark")) {
                int taskNumber = Integer.parseInt(String.valueOf(userInput.charAt(7)));
                if (taskNumber > taskCounter) {
                    System.out.printf("Chatty: task %d does not exist.\n", taskNumber);
                } else {
                    taskList[taskNumber - 1].unmarkDone();
                    System.out.printf("Chatty: I've marked this task as not done yet:\n%d: ", taskNumber);
                    System.out.println(taskList[taskNumber - 1]);
                }
                System.out.println(linebreak);
                continue;
            }

            taskList[taskCounter] = new Task(userInput);
            taskCounter++;
            System.out.println("Chatty: I have added task: " + userInput);
            System.out.println(linebreak);
        }
    }
}
