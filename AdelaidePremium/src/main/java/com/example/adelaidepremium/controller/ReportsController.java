package com.example.adelaidepremium.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class ReportsController {

    private void windows(String path, String title, ActionEvent event) throws Exception {

    double width = ((Node) event.getSource()).getScene().getWidth();
    double height = ((Node) event.getSource()).getScene().getHeight();

    URL url = new File(path).toURI().toURL();
    Parent root = FXMLLoader.load(url);
    Scene scene = new Scene(root, width, height);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
}
    @FXML
    public void report1Action(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/StocktakeReport.fxml", "Stocktake Report", event);
    }
    @FXML
    public void report2Action(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/QuarterlySalesReport.fxml", "Quarterly Sales Report", event);
    }
    @FXML
    public void report3Action(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/CustomerDemographicReport.fxml", "Customer Demographic Report", event);
    }
    @FXML
    public void report4Action(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/MonthlyTopProductsReport.fxml", "Monthly Top Products Report", event);
    }
    @FXML
    public void goBack(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/MainMenu.fxml", "Adelaide Premium", event);
    }
}
