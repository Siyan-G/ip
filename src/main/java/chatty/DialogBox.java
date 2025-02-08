package chatty;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box in the Chatty application.
 * <p>
 * A dialog box consists of an {@code ImageView} to display the speaker's avatar
 * and a {@code Label} containing the speaker's text. This class provides methods
 * to create user and Chatty response dialog boxes with appropriate formatting.
 * </p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and image.
     * <p>
     * This constructor loads the corresponding FXML file and initializes the dialog text and image.
     * </p>
     *
     * @param text The text message to be displayed.
     * @param img  The image representing the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box so that the {@code ImageView} appears on the left
     * and the text appears on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box representing user input.
     *
     * @param text The text message from the user.
     * @param img  The image representing the user.
     * @return A {@code DialogBox} instance for the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box representing Chatty's response.
     * <p>
     * This method also flips the dialog box layout so that Chatty's response appears
     * on the left side.
     * </p>
     *
     * @param text The text message from Chatty.
     * @param img  The image representing Chatty.
     * @return A {@code DialogBox} instance for Chatty's message.
     */
    public static DialogBox getChattyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
