module com.example.adelaidepremium {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;
    requires java.mail;

    opens com.example.adelaidepremium to javafx.fxml;
    exports com.example.adelaidepremium;

    exports com.example.adelaidepremium.entity;
    opens com.example.adelaidepremium.entity to javafx.fxml;

    exports com.example.adelaidepremium.controller;
    opens com.example.adelaidepremium.controller to javafx.fxml;

}