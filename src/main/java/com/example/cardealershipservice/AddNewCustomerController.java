package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

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
    private FirestoreService firestoreService = new FirestoreService();
    @FXML
    private Button confirmButton;

    public AddNewCustomerController() {
    }

    @FXML
    private void handleConfirm() {
        try {
            // Retrieve user input
            String name = nameField.getText();
            String contactInfo = contactInfoField.getText();
            String carModel = carModelField.getText();
            String vin = vinField.getText();
            String mileage = mileageField.getText();

            // Check if any field is empty
            if (name.isEmpty() || contactInfo.isEmpty() || carModel.isEmpty() || vin.isEmpty() || mileage.isEmpty()) {
                // Show warning alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Data");
                alert.setHeaderText("Some fields are empty");
                alert.setContentText("Do you still want to proceed with saving the data?");

                // Add buttons for user decision
                ButtonType proceedButton = new ButtonType("Proceed");
                ButtonType cancelButton = new ButtonType("Cancel");
                alert.getButtonTypes().setAll(proceedButton, cancelButton);

                // Wait for user response
                Optional<ButtonType> result = alert.showAndWait();

                // Handle user response
                if (result.isPresent() && result.get() == cancelButton) {
                    System.out.println("Operation canceled by user.");
                    return; // Stop further execution
                }
            }

            // Save data to Firestore if user confirms
            firestoreService.addCustomer(name, contactInfo, carModel, vin, mileage);
            System.out.println("Customer data saved successfully.");
            handleBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Stage currentStage = (Stage) confirmButton.getScene().getWindow();
            currentStage.setScene(new Scene(serviceQueuePage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
