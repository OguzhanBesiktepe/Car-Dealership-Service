package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
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

    private FirestoreService firestoreService;

    public AddNewCustomerController() {
        firestoreService = new FirestoreService();
    }

    @FXML
    private void handleConfirm() {
        try {
            // Collect input from fields
            String name = nameField.getText().trim();
            String contactInfo = contactInfoField.getText().trim();
            String carModel = carModelField.getText().trim();
            String vin = vinField.getText().trim();
            String mileage = mileageField.getText().trim();

            // Validate inputs
            if (name.isEmpty() || contactInfo.isEmpty() || carModel.isEmpty() || vin.isEmpty() || mileage.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Validation Error", "All fields must be filled in.");
                return;
            }

            // Add customer to Firestore
            firestoreService.addCustomer(name, contactInfo, carModel, vin, mileage);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer data saved successfully.");

            // Navigate back to ServiceQueue
            handleBack();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while saving customer data.");
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
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to navigate back to Service Queue.");
        }
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
