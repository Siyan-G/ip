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
        String[] taskList = new String[100];
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
                    System.out.printf("Chatty: your have %d tasks in progress:\n", taskCounter);
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

            taskList[taskCounter] = userInput;
            taskCounter++;
            System.out.println("Chatty: I have added task: " + userInput);
            System.out.println(linebreak);
        }
    }
}
