package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLoginAction() {
        try {
            // Load the Service Queue scene
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Scene serviceQueueScene = new Scene(serviceQueuePage);

            // Get the current stage from the login button
            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            // Set the new scene on the stage and show it
            currentStage.setScene(serviceQueueScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
