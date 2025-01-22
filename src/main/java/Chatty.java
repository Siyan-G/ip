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

        while (true) {
            System.out.print("Master: ");
            String userInput = scanner.nextLine();


            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(exitMsg);
                System.out.println(linebreak);
                break;
            }

            System.out.println("Chatty: " + userInput);
            System.out.println(linebreak);
        }
    }
}
