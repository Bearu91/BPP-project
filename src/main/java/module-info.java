module com.example.bpp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.bpp to javafx.fxml;
    exports com.example.bpp;
    exports com.example.bpp.algorithms;
    opens com.example.bpp.algorithms to javafx.fxml;
}