package com.example.cardealershipservice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    private static boolean isDarkMode = false; // Global theme state
    private static Scene mainScene; // Store the main scene for stylesheet updates

    @Override
    public void start(Stage stage) {
        try {
            // Load theme preference
            loadThemePreference();

            // Load the initial FXML file (Login-page)
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/cardealershipservice/Login-page.fxml"));
            Parent root = fxmlLoader.load();
            mainScene = new Scene(root, 800, 600);

            // Apply the appropriate theme
            applyTheme();

            // Set up the stage
            stage.setTitle("Car Dealership Service");
            stage.setScene(mainScene);
            stage.setResizable(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the FXML file. Please check the file path and resources.");
        }
    }

    /**
     * Toggles the theme between light and dark mode globally.
     */
    public static void toggleTheme() {
        isDarkMode = !isDarkMode; // Toggle the theme state
        applyTheme(); // Apply the updated theme
        saveThemePreference(); // Save the user's preference
    }

    /**
     * Applies the current theme by updating the main scene's stylesheets.
     */
    private static void applyTheme() {
        if (mainScene == null) return;

        mainScene.getStylesheets().clear(); // Clear existing stylesheets
        if (isDarkMode) {
            mainScene.getStylesheets().add(Main.class.getResource("/com/example/cardealershipservice/dark-mode.css").toExternalForm());
        } else {
            mainScene.getStylesheets().add(Main.class.getResource("/com/example/cardealershipservice/styles.css").toExternalForm());
        }
    }

    /**
     * Saves the user's theme preference to a file.
     */
    private static void saveThemePreference() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("theme-preference.txt"))) {
            writer.write(isDarkMode ? "dark" : "light");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the user's theme preference from a file.
     */
    private static void loadThemePreference() {
        try (BufferedReader reader = new BufferedReader(new FileReader("theme-preference.txt"))) {
            isDarkMode = "dark".equals(reader.readLine());
        } catch (IOException e) {
            isDarkMode = false; // Default to light mode
        }
    }

    /**
     * Returns whether the dark mode is currently active.
     * @return true if dark mode is active, false otherwise
     */
    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
