package com.example.adelaidepremium;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class MainController {
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
    public void browseCatalogueAction(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/BrowseCatalogue.fxml", "Browse Catalogue", event);
    }

    @FXML
    public void generateRandomCatalogueAction(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/GenerateRandomCatalogue.fxml", "Generate Random Catalogue", event);
    }

    @FXML
    public void salesAction(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/Sales.fxml", "Place Order", event);
    }

    @FXML
    public void reportsAction(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/ReportsPage.fxml", "Reports", event);
    }

    @FXML
    public void updateStockQuantityAction(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/UpdateStockQuantity.fxml", "Update Stock Quantity", event);
    }

}
