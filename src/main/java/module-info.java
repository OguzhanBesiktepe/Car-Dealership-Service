module com.example.cardealershipservice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cardealershipservice to javafx.fxml;
    exports com.example.cardealershipservice;
}