package com.example.cardealershipservice;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class BillingController {

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerEmailField;
    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;
    @FXML
    private VBox serviceList;

    @FXML
    private void handleAddService() {
        String service = serviceField.getText();
        String price = priceField.getText();
        if (!service.isEmpty() && !price.isEmpty()) {
            Label serviceDetail = new Label(service + ": $" + price);
            serviceList.getChildren().add(serviceDetail);
            serviceField.clear();
            priceField.clear();
        }
    }

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Stage currentStage = (Stage) serviceList.getScene().getWindow();
            currentStage.setScene(new Scene(serviceQueuePage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}