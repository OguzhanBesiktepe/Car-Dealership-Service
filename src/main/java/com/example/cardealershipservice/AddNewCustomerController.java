package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class AddNewCustomerController {

    // Declare text fields for customer data input
    @FXML
    private TextField nameField;         // Field for customer's name
    @FXML
    private TextField contactInfoField;  // Field for customer's contact information
    @FXML
    private TextField carModelField;     // Field for customer's car model
    @FXML
    private TextField vinField;          // Field for customer's car VIN (Vehicle Identification Number)
    @FXML
    private TextField mileageField;      // Field for the car's mileage

    // Create an instance of FirestoreService to interact with Firestore (database)
    private FirestoreService firestoreService = new FirestoreService();

    // Declare the confirm button which triggers saving customer data
    @FXML
    private Button confirmButton;

    // Handle the action when the confirm button is clicked
    @FXML
    private void handleConfirm() {
        try {
            // Retrieve the text entered in the fields
            String name = nameField.getText();
            String contactInfo = contactInfoField.getText();
            String carModel = carModelField.getText();
            String vin = vinField.getText();
            String mileage = mileageField.getText();

            // Call FirestoreService to save customer data to Firestore
            firestoreService.addCustomer(name, contactInfo, carModel, vin, mileage);

            // Print a success message to the console
            System.out.println("Customer data saved successfully.");

            // Navigate back to the ServiceQueue screen after saving data
            handleBack();
        } catch (Exception e) {
            // Print any errors that occur during the process
            e.printStackTrace();
        }
    }

    // Handle the action when the back button is clicked (navigating to ServiceQueue)
    @FXML
    private void handleBack() {
        try {
            // Load the ServiceQueue.fxml file which represents the service queue page
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));

            // Get the current stage (window) from the confirmButton's scene
            Stage currentStage = (Stage) confirmButton.getScene().getWindow();

            // Set the scene of the current stage to the service queue page
            currentStage.setScene(new Scene(serviceQueuePage));

            // Show the service queue page
            currentStage.show();
        } catch (Exception e) {
            // Print any errors that occur during scene transition
            e.printStackTrace();
        }
    }
}
