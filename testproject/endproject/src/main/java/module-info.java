module com.example.endproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.calendarfx.view;


    //opens com.example.endproject to javafx.fxml;
    exports com.example.endproject;
    exports com.example.endproject.app;
    opens com.example.endproject.app to javafx.fxml;
    exports com.example.endproject.controller;
    opens com.example.endproject.controller to javafx.fxml;
}