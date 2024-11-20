package com.example.cardealershipservice;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonObject;

public class ServiceQueueController {

    @FXML
    private TextField waitingNameField;
    @FXML
    private TextField serviceNameField;
    @FXML
    private TextField searchField;
    @FXML
    private VBox waitingList;
    @FXML
    private VBox serviceList;
    @FXML
    private VBox searchResults;
    @FXML
    private Button addNewCustomerButton; // Ensure this matches fx:id in FXML
    @FXML
    private Button goToBillingButton;
    @FXML
    private Button goToAccessoriesButton;
    @FXML
    private Button goToServiceDetailsButton;

    private FirestoreService firestoreService;

    public ServiceQueueController() {
        firestoreService = new FirestoreService();
    }

    @FXML
    private void initialize() {
        // Initialization logic if needed
    }

    @FXML
    private void handleAddToWaiting() {
        String name = waitingNameField.getText();
        if (!name.isEmpty()) {
            Label customer = new Label(name);
            waitingList.getChildren().add(customer);
            waitingNameField.clear();
        }
    }

    @FXML
    private void handleAddToService() {
        String name = serviceNameField.getText();
        if (!name.isEmpty()) {
            Label customer = new Label(name);
            serviceList.getChildren().add(customer);
            serviceNameField.clear();
        }
    }

    @FXML
    private void handleSearch() {
        String name = searchField.getText();
        if (name.isEmpty()) {
            searchResults.getChildren().clear();
            searchResults.getChildren().add(new Label("Please enter a name to search."));
            return;
        }

        searchResults.getChildren().clear();

        CompletableFuture.runAsync(() -> {
            try {
                List<JsonObject> customers = firestoreService.searchCustomerByName(name);
                Platform.runLater(() -> {
                    if (customers.isEmpty()) {
                        searchResults.getChildren().add(new Label("No customers found with the name: " + name));
                    } else {
                        for (JsonObject customer : customers) {
                            Label customerLabel = new Label(
                                    "Name: " + customer.get("name").getAsJsonObject().get("stringValue").getAsString() +
                                            ", Contact: " + customer.get("contactInfo").getAsJsonObject().get("stringValue").getAsString() +
                                            ", Car Model: " + customer.get("carModel").getAsJsonObject().get("stringValue").getAsString()
                            );
                            searchResults.getChildren().add(customerLabel);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> searchResults.getChildren().add(new Label("Error searching for customers.")));
            }
        });
    }

    @FXML
    private void handleAddNewCustomer() {
        loadScene("/com/example/cardealershipservice/AddNewCustomer.fxml");
    }

    @FXML
    private void handleGoToBilling() {
        loadScene("/com/example/cardealershipservice/Billing.fxml");
    }

    @FXML
    private void handleGoToAccessories() {
        loadScene("/com/example/cardealershipservice/AddAccessories.fxml");
    }

    @FXML
    private void handleGoToServiceDetails() {
        loadScene("/com/example/cardealershipservice/AddServiceDetails.fxml");
    }

    @FXML
    private void handleBack() {
        loadScene("/com/example/cardealershipservice/Login-page.fxml");
    }

    private void loadScene(String fxmlPath) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage currentStage = (Stage) addNewCustomerButton.getScene().getWindow();
            currentStage.setScene(new Scene(page));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
