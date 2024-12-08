package com.example.cardealershipservice;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane rootPane; // The root container of the Login page

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

    @FXML
    private ToggleButton themeToggleButton; // Toggle button for switching between light and dark themes

    private FirebaseAuthService firebaseAuthService = new FirebaseAuthService();

    private boolean isDarkMode = false; // Tracks whether the dark mode is currently active (default is light mode)

    public LoginController() {
    }

    @FXML
    private void initialize() {
        // Ensure default light mode styles are applied on startup
        rootPane.setStyle("-fx-background-color: lightyellow;");
        usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-text-fill: black;");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-text-fill: black;");
        loginButton.setStyle("-fx-background-color: linear-gradient(to top, #6a5acd, #ff7f50); -fx-text-fill: white;");
        registerButton.setStyle("-fx-background-color: linear-gradient(to top, #6a5acd, #ff7f50); -fx-text-fill: white;");
        forgotPasswordButton.setStyle("-fx-background-color: linear-gradient(to top, #6a5acd, #ff7f50); -fx-text-fill: white;");
    }

    @FXML
    private void handleLoginAction() {
        try {
            String email = this.usernameField.getText();
            String password = this.passwordField.getText();

            // Validate email and password inputs
            if (!this.isValidEmail(email)) {
                this.showAlert(AlertType.ERROR, "Validation Error", "Invalid Email", "Please enter a valid email address.");
                return;
            }

            if (!this.isValidPassword(password)) {
                this.showAlert(AlertType.ERROR, "Validation Error", "Invalid Password", "Password must be at least 6 characters long.");
                return;
            }

            // Authenticate the user with Firebase
            String idToken = this.firebaseAuthService.signInWithEmailPassword(email, password);
            System.out.println("Login Successful, ID Token: " + idToken);

            // Load the service queue page after successful login
            Parent serviceQueuePage = FXMLLoader.load(this.getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Scene serviceQueueScene = new Scene(serviceQueuePage);
            Stage currentStage = (Stage) this.loginButton.getScene().getWindow();
            currentStage.setScene(serviceQueueScene);
            currentStage.show();
        } catch (Exception e) {
            // Show an error alert if login fails
            showAlert(AlertType.ERROR, "Login Error", "Login Failed", "Incorrect email or password. Please try again.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegisterRedirect() {
        try {
            // Redirect to the registration page
            Parent registerPage = FXMLLoader.load(this.getClass().getResource("/com/example/cardealershipservice/Register.fxml"));
            Scene registerScene = new Scene(registerPage);
            Stage currentStage = (Stage) this.registerButton.getScene().getWindow();
            currentStage.setScene(registerScene);
            currentStage.show();
        } catch (Exception e) {
            // Show an error alert if navigation fails
            showAlert(AlertType.ERROR, "Navigation Error", "Unable to Navigate", "Could not load the registration page.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleForgotPassword() {
        try {
            // Prompt the user to enter their email for password reset
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Forgot Password");
            dialog.setHeaderText("Reset Your Password");
            dialog.setContentText("Please enter your email:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent((email) -> {
                try {
                    // Send a password reset email via Firebase
                    this.firebaseAuthService.sendPasswordResetEmail(email);
                    showAlert(AlertType.INFORMATION, "Password Reset Email Sent", null, "A password reset email has been sent to: " + email);
                } catch (Exception e) {
                    // Show an error alert if the email fails to send
                    showAlert(AlertType.ERROR, "Error", null, "Failed to send password reset email. Please try again.");
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleThemeToggle() {
        // Handles switching between light mode and dark mode
        if (isDarkMode) {
            // Switch to Light Mode
            rootPane.setStyle("-fx-background-color: lightyellow;");
            usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-text-fill: black;");
            passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-text-fill: black;");
            loginButton.setStyle("-fx-background-color: linear-gradient(to top, #6a5acd, #ff7f50); -fx-text-fill: white;");
            registerButton.setStyle("-fx-background-color: linear-gradient(to top, #6a5acd, #ff7f50); -fx-text-fill: white;");
            forgotPasswordButton.setStyle("-fx-background-color: linear-gradient(to top, #6a5acd, #ff7f50); -fx-text-fill: white;");
            themeToggleButton.setText("Dark Mode");
        } else {
            // Switch to Dark Mode
            rootPane.setStyle("-fx-background-color: #2e2e2e;");
            usernameField.setStyle("-fx-background-color: #424242; -fx-border-color: #05c8e5; -fx-text-fill: white;");
            passwordField.setStyle("-fx-background-color: #424242; -fx-border-color: #05c8e5; -fx-text-fill: white;");
            loginButton.setStyle("-fx-background-color: #1a73e8; -fx-text-fill: white;");
            registerButton.setStyle("-fx-background-color: #1a73e8; -fx-text-fill: white;");
            forgotPasswordButton.setStyle("-fx-background-color: #1a73e8; -fx-text-fill: white;");
            themeToggleButton.setText("Light Mode");
        }
        isDarkMode = !isDarkMode; // Toggle theme state
    }

    private boolean isValidEmail(String email) {
        // Validate email format using a regex
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        // Validate that the password is at least 6 characters long
        return password != null && password.length() > 5;
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        // Display a popup alert with the specified type and content
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
