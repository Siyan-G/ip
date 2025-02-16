package chatty;

import javafx.application.Application;

/**
 * The {@code Launcher} class serves as an entry point to start the Chatty application.
 * <p>
 * This class acts as a workaround for JavaFX classpath issues that may arise when launching
 * the application directly from a JAR file. It delegates the execution to {@link Main}.
 * </p>
 */
public class Launcher {
    /**
     * Launches the Chatty application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
