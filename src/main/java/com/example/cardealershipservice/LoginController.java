package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.util.Optional;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button forgotPasswordButton;

    private FirebaseAuthService firebaseAuthService = new FirebaseAuthService();

    /**
     * Handle login action.
     */
    @FXML
    private void handleLoginAction() {
        try {
            String email = usernameField.getText();
            String password = passwordField.getText();

            String idToken = firebaseAuthService.signInWithEmailPassword(email, password);

            System.out.println("Login Successful, ID Token: " + idToken);

            // Navigate to the Service Queue page
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Scene serviceQueueScene = new Scene(serviceQueuePage);
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.setScene(serviceQueueScene);
            currentStage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Login Failed");
            alert.setContentText("Incorrect email or password. Please try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Redirect to the Register page.
     */
    @FXML
    private void handleRegisterRedirect() {
        try {
            Parent registerPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/Register.fxml"));
            Scene registerScene = new Scene(registerPage);
            Stage currentStage = (Stage) registerButton.getScene().getWindow();
            currentStage.setScene(registerScene);
            currentStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Unable to Navigate");
            alert.setContentText("Could not load the registration page.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Handle "Forgot Password" action.
     */
    @FXML
    private void handleForgotPassword() {
        try {
            // Prompt the user for their email
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Forgot Password");
            dialog.setHeaderText("Reset Your Password");
            dialog.setContentText("Please enter your email:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(email -> {
                try {
                    firebaseAuthService.sendPasswordResetEmail(email);

                    // Show success alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Reset Email Sent");
                    alert.setHeaderText(null);
                    alert.setContentText("A password reset email has been sent to: " + email);
                    alert.showAndWait();
                } catch (Exception e) {
                    // Show error alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to send password reset email. Please try again.");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}