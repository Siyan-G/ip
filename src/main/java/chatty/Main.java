package chatty;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for launching the Chatty application with a GUI.
 * <p>
 * This class extends {@link Application} to initialize and display the JavaFX user interface.
 * It loads the FXML layout, sets up the main application window, and connects the GUI to the
 * {@link Chatty} backend for handling user interactions.
 * </p>
 */
public class Main extends Application {

    private final Chatty chatty = new Chatty("./data/tasks.csv");

    /**
     * Starts the JavaFX application by setting up the main window.
     * <p>
     * This method loads the {@code MainWindow.fxml} layout, initializes the scene,
     * and injects the {@link Chatty} instance into the controller.
     * </p>
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/logo.png")));
            stage.setTitle("Chatty");
            stage.setResizable(true);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setChatty(chatty); // inject the Chatty instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
