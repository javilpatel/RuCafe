package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    /**
     * Starts the JavaFX Application to run the GUI
     * @param stage stage instance that sets the scene for the application
     * @throws IOException exception to handle any errors for loading fxml files.
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RU Cafe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to call the "start" method
     * @param args String array of any additional arguments needed when running the program
     */
    public static void main(String[] args) {
        launch();
    }
}