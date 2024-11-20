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

public class AddServiceDetailsController {

    // TextField for entering the service detail description
    @FXML
    private TextField serviceDetailField;

    // TextField for entering the service cost
    @FXML
    private TextField serviceCostField;

    // VBox to display the list of added service details
    @FXML
    private VBox serviceDetailList;

    // Instance of FirestoreService for interacting with Firestore database
    private FirestoreService firestoreService = new FirestoreService();

    // A placeholder for the customer ID, to be replaced with actual data from your system
    private String customerId = "customer_document_id";

    // Button to trigger the action of adding a service detail
    @FXML
    private Button addServiceDetailButton;

    // Button to navigate back to the Service Queue page
    @FXML
    private Button backButton;

    // Method to handle adding a new service detail when the "Add Service Detail" button is clicked
    @FXML
    private void handleAddServiceDetail() {
        try {
            // Get the text entered in the service detail and service cost fields
            String detail = serviceDetailField.getText();
            String cost = serviceCostField.getText();

            // Ensure that both fields are filled out before proceeding
            if (!detail.isEmpty() && !cost.isEmpty()) {
                // Add the service detail to the Firestore database
                firestoreService.addServiceDetail(customerId, detail, cost);

                // Create a new label to display the service detail and cost in the UI
                Label detailLabel = new Label(detail + ": $" + cost);

                // Add the newly created label to the VBox that holds the list of service details
                serviceDetailList.getChildren().add(detailLabel);

                // Clear the input fields for the next entry
                serviceDetailField.clear();
                serviceCostField.clear();
            } else {
                // Print a message if either of the fields is empty
                System.out.println("Please fill in both service detail and cost fields.");
            }
        } catch (Exception e) {
            // Handle any exceptions and print the error message
            e.printStackTrace();
            System.out.println("Error adding service detail: " + e.getMessage());
        }
    }

    // Method to handle the back navigation when the "Back" button is clicked
    @FXML
    private void handleBack() {
        try {
            // Load the ServiceQueue.fxml file (the Service Queue page)
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));

            // Get the current window (Stage) where the button is located
            Stage currentStage = (Stage) backButton.getScene().getWindow();

            // Set the new scene (ServiceQueue.fxml) on the current stage
            currentStage.setScene(new Scene(serviceQueuePage));

            // Show the updated stage with the new scene
            currentStage.show();
        } catch (Exception e) {
            // Handle any exceptions that occur when loading the ServiceQueue page
            e.printStackTrace();
            System.out.println("Error loading ServiceQueue.fxml: " + e.getMessage());
        }
    }
}
