package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class ServiceQueueController {

    @FXML
    private Button goToBillingButton;

    @FXML
    private void handleGoToBilling() {
        try {
            Parent billingPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/Billing.fxml"));
            Scene billingScene = new Scene(billingPage);
            Stage currentStage = (Stage) goToBillingButton.getScene().getWindow();
            currentStage.setScene(billingScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button addNewCustomerButton;

    @FXML
    private void handleAddNewCustomer() {
        try {
            Parent addNewCustomerPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/AddNewCustomer.fxml"));
            Scene addNewCustomerScene = new Scene(addNewCustomerPage);
            Stage currentStage = (Stage) addNewCustomerButton.getScene().getWindow();
            currentStage.setScene(addNewCustomerScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            Parent loginPage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/Login-page.fxml"));
            Scene loginScene = new Scene(loginPage);
            Stage currentStage = (Stage) goToBillingButton.getScene().getWindow();
            currentStage.setScene(loginScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddService() {
        System.out.println("Add Service Clicked");
    }

    @FXML
    private void handleRemoveService() {
        System.out.println("Remove Service Clicked");
    }
}
