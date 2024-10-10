package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class AddNewCustomerController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField contactInfoField;
    @FXML
    private TextField carModelField;
    @FXML
    private TextField vinField;
    @FXML
    private TextField mileageField;

    @FXML
    private Button confirmButton;

    @FXML
    private void handleConfirm() {
        // Capture the inputted data
        String name = nameField.getText();
        String contactInfo = contactInfoField.getText();
        String carModel = carModelField.getText();
        String vin = vinField.getText();
        String mileage = mileageField.getText();

        System.out.println("New Customer Added: " + name);

        // After confirming, return to the service queue screen
        handleBack();
    }

    @FXML
    private Button backButton;

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Scene serviceQueueScene = new Scene(serviceQueuePage);
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.setScene(serviceQueueScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
