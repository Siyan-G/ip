package chatty;

import chatty.exception.ChattyException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The {@code MainWindow} class serves as the controller for the Chatty application's main GUI.
 * <p>
 * It manages user interactions by handling text input, displaying responses, and maintaining
 * the chat layout. This class initializes the JavaFX components and binds UI elements dynamically.
 * </p>
 */
public class MainWindow extends AnchorPane {

    private static final String INTRO_MSG = "Hello Master! I'm Chatty, your ever-ready personal assistant."
            + " How can I help you today?"
            + "\nType \"help\" and click \"Send\" to view the list of command available";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chatty chatty;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaChatty.png"));

    /**
     * Initializes the GUI components.
     * <p>
     * This method ensures the scroll pane automatically scrolls down as new messages
     * are added to the dialog container.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getChattyDialog(INTRO_MSG, dukeImage)
        );
    }

    /**
     * Injects the {@link Chatty} instance into the controller.
     *
     * @param chatty The Chatty instance that handles user commands.
     */
    public void setChatty(Chatty chatty) {
        this.chatty = chatty;
    }

    /**
     * Handles user input by creating and displaying dialog boxes.
     * <p>
     * This method retrieves user input, processes it using {@link Chatty#getResponse(String)},
     * and displays both the user input and Chatty's response in the dialog container.
     * The text field is cleared after processing.
     * </p>
     *
     * @throws ChattyException If an error occurs while processing the user input.
     */
    @FXML
    private void handleUserInput() throws ChattyException {
        String input = userInput.getText();
        String response = chatty.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChattyDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
