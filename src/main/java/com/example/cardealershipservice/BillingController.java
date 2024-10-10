package com.example.cardealershipservice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class BillingController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBack() {
        try {
            Parent serviceQueuePage = FXMLLoader.load(getClass().getResource("/com/example/cardealershipservice/ServiceQueue.fxml"));
            Scene serviceQueueScene = new Scene(serviceQueuePage);
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.setScene(serviceQueueScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
