package com.example.cardealershipservice;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button forgotPasswordButton;

    private FirebaseAuthService firebaseAuthService = new FirebaseAuthService();

    public LoginController() {
    }

    @FXML
    private void initialize() {
        // No need for Caps Lock check anymore
    }

    @FXML
    private void handleLoginAction() {
        try {
            String email = this.usernameField.getText();
            String password = this.passwordField.getText();

            if (!this.isValidEmail(email)) {
                this.showAlert(AlertType.ERROR, "Validation Error", "Invalid Email", "Please enter a valid email address.");
                return;
            }

            if (!this.isValidPassword(password)) {
                this.showAlert(AlertType.ERROR, "Validation Error", "Invalid Password", "Password must be at least 6 characters long.");
                return;
            }

            String idToken = this.firebaseAuthService.signInWithEmailPassword(email, password);
            System.out.println("Login Successful, ID Token: " + idToken);
            Parent serviceQueuePage = FXMLLoader.load(this.getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Scene serviceQueueScene = new Scene(serviceQueuePage);
            Stage currentStage = (Stage) this.loginButton.getScene().getWindow();
            currentStage.setScene(serviceQueueScene);
            currentStage.show();
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Login Error", "Login Failed", "Incorrect email or password. Please try again.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegisterRedirect() {
        try {
            Parent registerPage = FXMLLoader.load(this.getClass().getResource("/com/example/cardealershipservice/Register.fxml"));
            Scene registerScene = new Scene(registerPage);
            Stage currentStage = (Stage) this.registerButton.getScene().getWindow();
            currentStage.setScene(registerScene);
            currentStage.show();
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Navigation Error", "Unable to Navigate", "Could not load the registration page.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleForgotPassword() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Forgot Password");
            dialog.setHeaderText("Reset Your Password");
            dialog.setContentText("Please enter your email:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent((email) -> {
                try {
                    this.firebaseAuthService.sendPasswordResetEmail(email);
                    showAlert(AlertType.INFORMATION, "Password Reset Email Sent", null, "A password reset email has been sent to: " + email);
                } catch (Exception e) {
                    showAlert(AlertType.ERROR, "Error", null, "Failed to send password reset email. Please try again.");
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() > 5; // Updated validation
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
