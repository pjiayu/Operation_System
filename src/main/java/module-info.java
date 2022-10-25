module com.example.operation_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.operation_system to javafx.fxml;
    exports com.example.operation_system;
    exports com.example.operation_system.Controller;
    opens com.example.operation_system.Controller to javafx.fxml;
    opens com.example.operation_system.model to javafx.base;
}