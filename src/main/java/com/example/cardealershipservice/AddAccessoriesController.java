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

public class AddAccessoriesController {

    @FXML
    private TextField accessoryNameField;
    @FXML
    private TextField accessoryPriceField;
    @FXML
    private VBox accessoryList;

    private FirestoreService firestoreService = new FirestoreService();
    private String customerId = "customer_document_id";  // Replace with the actual customer ID

    @FXML
    private Button addAccessoryButton;

    @FXML
    private void handleAddAccessory() {
        try {
            String name = accessoryNameField.getText();
            String price = accessoryPriceField.getText();
            if (!name.isEmpty() && !price.isEmpty()) {
                firestoreService.addAccessory(customerId, name, price);

                Label accessoryDetail = new Label(name + ": $" + price);
                accessoryList.getChildren().add(accessoryDetail);
                accessoryNameField.clear();
                accessoryPriceField.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Stage currentStage = (Stage) addAccessoryButton.getScene().getWindow();
            currentStage.setScene(new Scene(serviceQueuePage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
