package chatty.controller;

import java.util.Scanner;

public class Ui {
    private static final String LINE_BREAK = "___________________________________________________________________";
    private static final String INTRO_MSG = "Hello Master! I'm chatty.Chatty, your ever-ready personal assistant." +
            "\nHow can I help you today?";
    private static final String EXIT_MSG = "Goodbye Master! See you soon!";
    private static final String HELP_MSG = "Help is coming";
    private Scanner scanner = new Scanner(System.in);

    public void sendIntroMsg() {
        System.out.println(LINE_BREAK);
        sendMessage(INTRO_MSG);
    }

    public void sendExitMsg() {
        scanner.close();
        sendMessage(EXIT_MSG);
    }

    public void sendMessage(String msg) {
        System.out.println("Chatty:");
        System.out.println(msg);
        System.out.println(LINE_BREAK);
    }

    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void showHelp() {
        sendMessage(HELP_MSG);
    }

    public void showError(String msg) {
        System.out.println("ERROR!! " + msg);
    }
}
