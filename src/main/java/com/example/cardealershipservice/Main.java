package com.example.cardealershipservice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/cardealershipservice/Login-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);

            // Optional: Load a stylesheet (uncomment if needed)
            // scene.getStylesheets().add(Main.class.getResource("/com/example/cardealershipservice/styles.css").toExternalForm());

            // Set up the stage
            stage.setTitle("Car Dealership Service");
            stage.setScene(scene);
            stage.setResizable(true); // Allow resizing of the window
            stage.show();

        } catch (IOException e) {
            // Print the stack trace for debugging
            e.printStackTrace();

            // Display a user-friendly error message in the console
            System.err.println("Failed to load the FXML file. Please check the file path and resources.");
        }
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
