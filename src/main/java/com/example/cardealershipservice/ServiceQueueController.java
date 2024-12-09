package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class ServiceQueueController {

    @FXML
    private TextField waitingNameField;
    @FXML
    private TextField serviceNameField;
    @FXML
    private VBox waitingList;
    @FXML
    private VBox serviceList;
    @FXML
    private Button goToBillingButton;
    @FXML
    private Button goToAccessoriesButton;
    @FXML
    private Button goToServiceDetailsButton;
    @FXML
    private Pane mainPane; // The root pane that will have its background color changed
    @FXML
    private ToggleButton themeToggleButton; // Reference to the theme toggle button
    @FXML
    private boolean isDarkMode = false; // Tracks the current theme state

    @FXML
    private void handleThemeToggle() {
        // Toggle the theme state
        isDarkMode = !isDarkMode;

        // Apply dark or light theme styles
        if (isDarkMode) {
            mainPane.setStyle("-fx-background-color: #2B2B2B; -fx-text-fill: white;");
            themeToggleButton.setText("Light Mode");
            themeToggleButton.setStyle("-fx-background-color: linear-gradient(to top, #808080, #ffa500); -fx-border-radius: 10; -fx-text-fill: white;");
        } else {
            mainPane.setStyle("-fx-background-color: lightyellow; -fx-text-fill: black;");
            themeToggleButton.setText("Dark Mode");
            themeToggleButton.setStyle("-fx-background-color: linear-gradient(to top, #ffa500, #808080); -fx-border-radius: 10; -fx-text-fill: #808080;");
        }
    }

    @FXML
    private void handleAddToWaiting() {
        String name = waitingNameField.getText();
        if (!name.isEmpty()) {
            Label customer = new Label(name);
            waitingList.getChildren().add(customer);
            waitingNameField.clear();
        }
    }

    @FXML
    private void handleAddToService() {
        String name = serviceNameField.getText();
        if (!name.isEmpty()) {
            Label customer = new Label(name);
            serviceList.getChildren().add(customer);
            serviceNameField.clear();
        }
    }

    @FXML
    private void handleGoToBilling() {
        try {
            Parent billingPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/Billing.fxml"));
            Stage currentStage = (Stage) goToBillingButton.getScene().getWindow();
            currentStage.setScene(new Scene(billingPage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddNewCustomer() {
        try {
            Parent addNewCustomerPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/AddNewCustomer.fxml"));
            Stage currentStage = (Stage) goToBillingButton.getScene().getWindow();
            currentStage.setScene(new Scene(addNewCustomerPage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoToAccessories() {
        try {
            Parent accessoriesPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/AddAccessories.fxml"));
            Stage currentStage = (Stage) goToAccessoriesButton.getScene().getWindow();
            currentStage.setScene(new Scene(accessoriesPage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoToServiceDetails() {
        try {
            Parent serviceDetailsPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/AddServiceDetails.fxml"));
            Stage currentStage = (Stage) goToServiceDetailsButton.getScene().getWindow();
            currentStage.setScene(new Scene(serviceDetailsPage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/login-page.fxml"));
            Stage currentStage = (Stage) goToBillingButton.getScene().getWindow();
            currentStage.setScene(new Scene(loginPage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
