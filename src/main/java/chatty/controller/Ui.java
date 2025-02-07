package chatty.controller;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user in the chatty application.
 * <p>
 * This class provides methods to send messages to the user, display the introduction and exit messages,
 * read user commands, and show help or error messages. It serves as the user interface component for the
 * chatty application, ensuring the user has an interactive and informative experience.
 * </p>
 */
public class Ui {
    private static final String LINE_BREAK = "___________________________________________________________________";
    private static final String INTRO_MSG = "Hello Master! I'm chatty.Chatty, your ever-ready personal assistant."
            + "\nHow can I help you today?";
    private static final String EXIT_MSG = "Goodbye Master! See you soon!";
    private static final String HELP_MSG = "Help is coming";
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the introduction message to the user.
     * <p>
     * This message welcomes the user to the application and offers help.
     * </p>
     */
    public void sendIntroMsg() {
        System.out.println(LINE_BREAK);
        sendMessage(INTRO_MSG);
    }

    /**
     * Closes the scanner and displays the exit message.
     * <p>
     * This method is called when the user decides to exit the application.
     * </p>
     */
    public void sendExitMsg() {
        scanner.close();
        sendMessage(EXIT_MSG);
    }

    /**
     * Displays a message to the user.
     *
     * @param msg The message to be sent to the user.
     */
    public void sendMessage(String msg) {
        System.out.println("Chatty:");
        System.out.println(msg);
        System.out.println(LINE_BREAK);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Displays a help message to the user.
     * <p>
     * This message provides instructions or general help to the user.
     * </p>
     */
    public void showHelp() {
        sendMessage(HELP_MSG);
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg The error message to be displayed.
     */
    public void showError(String msg) {
        System.out.println("ERROR!! " + msg);
    }
}
