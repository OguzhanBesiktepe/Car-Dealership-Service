module com.example.cardealershipservice {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires com.google.gson;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires java.desktop;


    opens com.example.cardealershipservice to javafx.fxml;
    exports com.example.cardealershipservice;
}