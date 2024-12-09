package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.text.DecimalFormat;

public class BillingController {

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerIDField;

    @FXML
    private TextField serviceDescriptionField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    private double subtotal = 0.0;
    private final double TAX_RATE = 0.07; // Example tax rate (7%)

    private final DecimalFormat df = new DecimalFormat("#.00");

    @FXML
    private void initialize() {
        // Initialize the billing summary fields
        updateSummary();
    }

    @FXML
    private void handleAddItem() {
        String description = serviceDescriptionField.getText();
        String quantityText = quantityField.getText();
        String priceText = priceField.getText();

        if (description.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            // Display error or validation message (optional)
            System.out.println("All fields must be filled out to add an item.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);
            double itemTotal = quantity * price;

            // Add the item to the subtotal
            subtotal += itemTotal;

            // Update the billing summary
            updateSummary();

            // Clear the input fields for new entry
            serviceDescriptionField.clear();
            quantityField.clear();
            priceField.clear();
        } catch (NumberFormatException e) {
            // Handle invalid numeric inputs
            System.out.println("Quantity and price must be valid numbers.");
        }
    }

    @FXML
    private void handleConfirmPayment() {
        // Confirm payment and reset the form
        System.out.println("Payment confirmed for total: $" + df.format(subtotal * (1 + TAX_RATE)));

        // Reset fields
        customerNameField.clear();
        customerIDField.clear();
        serviceDescriptionField.clear();
        quantityField.clear();
        priceField.clear();
        subtotal = 0.0;

        // Update summary after reset
        updateSummary();
    }

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Stage currentStage = (Stage) customerNameField.getScene().getWindow();
            currentStage.setScene(new Scene(serviceQueuePage));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSummary() {
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        // Update labels with formatted values
        subtotalLabel.setText("Subtotal: $" + df.format(subtotal));
        taxLabel.setText("Tax: $" + df.format(tax));
        totalLabel.setText("Total: $" + df.format(total));
    }
}
