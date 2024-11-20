package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class AddAccessoriesController {

    // TextField for entering the accessory name
    @FXML
    private TextField accessoryNameField;

    // TextField for entering the accessory price
    @FXML
    private TextField accessoryPriceField;

    // VBox to display the list of added accessories
    @FXML
    private VBox accessoryList;

    // Instance of FirestoreService for interacting with Firestore database
    private FirestoreService firestoreService = new FirestoreService();

    // A placeholder for the customer ID, to be replaced with actual data from your system
    private String customerId = "customer_document_id";  // Replace with the actual customer ID

    // Button to trigger the action of adding an accessory
    @FXML
    private Button addAccessoryButton;

    // Button to navigate back to the Service Queue page
    @FXML
    private Button backButton;

    // Method to handle adding a new accessory when the "Add Accessory" button is clicked
    @FXML
    private void handleAddAccessory() {
        try {
            // Get the text entered in the accessory name and accessory price fields
            String name = accessoryNameField.getText();
            String price = accessoryPriceField.getText();

            // Ensure that both fields are filled out before proceeding
            if (!name.isEmpty() && !price.isEmpty()) {
                // Add the accessory details to the Firestore database
                firestoreService.addAccessory(customerId, name, price);

                // Create a new label to display the accessory name and price in the UI
                Label accessoryDetail = new Label(name + ": $" + price);

                // Add the newly created label to the VBox that holds the list of accessories
                accessoryList.getChildren().add(accessoryDetail);

                // Clear the input fields after adding the accessory
                accessoryNameField.clear();
                accessoryPriceField.clear();
            }
        } catch (Exception e) {
            // Handle any exceptions and print the error message
            e.printStackTrace();
        }
    }

    // Method to handle the back navigation when the "Back" button is clicked
    @FXML
    private void handleBackToQueue(ActionEvent event) {
        try {
            // Load the ServiceQueue.fxml file (the Service Queue page)
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));

            // Get the current window (Stage) where the back button is located
            Stage currentStage = (Stage) backButton.getScene().getWindow();

            // Set the new scene (ServiceQueue.fxml) on the current stage
            currentStage.setScene(new Scene(serviceQueuePage));

            // Show the updated stage with the new scene
            currentStage.show();
        } catch (Exception e) {
            // Handle any exceptions that occur when loading the ServiceQueue page
            e.printStackTrace();
        }
    }
}
