package com.example.cardealershipservice;

import javafx.fxml.FXML;
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

    private FirestoreService firestoreService = new FirestoreService();

    @FXML
    private Button confirmButton;

    @FXML
    private void handleConfirm() {
        try {
            String name = nameField.getText();
            String contactInfo = contactInfoField.getText();
            String carModel = carModelField.getText();
            String vin = vinField.getText();
            String mileage = mileageField.getText();

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