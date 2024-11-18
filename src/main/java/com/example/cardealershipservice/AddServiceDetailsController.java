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

    @FXML
    private TextField serviceDetailField;
    @FXML
    private TextField serviceCostField;
    @FXML
    private VBox serviceDetailList;

    private FirestoreService firestoreService = new FirestoreService();
    private String customerId = "customer_document_id";  // Replace with the actual customer ID

    @FXML
    private Button addServiceDetailButton;

    @FXML
    private void handleAddServiceDetail() {
        try {
            String detail = serviceDetailField.getText();
            String cost = serviceCostField.getText();
            if (!detail.isEmpty() && !cost.isEmpty()) {
                firestoreService.addServiceDetail(customerId, detail, cost);

                Label detailLabel = new Label(detail + ": $" + cost);
                serviceDetailList.getChildren().add(detailLabel);
                serviceDetailField.clear();
                serviceCostField.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Stage currentStage = (Stage) addServiceDetailButton.getScene().getWindow();
            currentStage.setScene(new Scene(serviceQueuePage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}