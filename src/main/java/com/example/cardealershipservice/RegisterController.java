package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class RegisterController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private FirebaseAuthService firebaseAuthService = new FirebaseAuthService();

    @FXML
    private void handleRegisterAction() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            // Call the Firebase Auth Service to create a new user
            firebaseAuthService.createUserWithEmailPassword(email, password);

            // Show a success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Account created successfully. Redirecting to login...");
            alert.showAndWait();

            // Navigate back to the login page
            Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/Login-page.fxml"));
            Scene loginScene = new Scene(loginPage);
            Stage currentStage = (Stage) emailField.getScene().getWindow();
            currentStage.setScene(loginScene);
            currentStage.show();

        } catch (Exception e) {
            // Show an error alert if registration fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Failed to create account. Please try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
