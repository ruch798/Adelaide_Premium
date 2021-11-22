package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Sales;
import com.example.adelaidepremium.model.SalesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MonthlyTopProductsReportController implements Initializable {

    @FXML
    private TableView<Sales> table1,table2, table3;

    @FXML
    private TableColumn<Sales, String> ProductNameColumn1, ProductNameColumn2, ProductNameColumn3;

    @FXML
    private TableColumn<Sales, Integer> TotalQuantitySoldColumn1, TotalQuantitySoldColumn2, TotalQuantitySoldColumn3;

    private SalesModel salesModel;

    private ObservableList<Sales> SALESLIST1, SALESLIST2, SALESLIST3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salesModel = new SalesModel();
        SALESLIST1 = FXCollections.observableArrayList();
        SALESLIST2 = FXCollections.observableArrayList();
        SALESLIST3 = FXCollections.observableArrayList();

        loadTopProductsCharts();
    }

    private void loadTopProductsCharts(){

        SALESLIST1.addAll(salesModel.getTop10ProductsFromDatabase("Shirt"));
        ProductNameColumn1.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        TotalQuantitySoldColumn1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table1.setItems(SALESLIST1);

        SALESLIST2.addAll(salesModel.getTop10ProductsFromDatabase("Jacket"));
        ProductNameColumn2.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        TotalQuantitySoldColumn2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table2.setItems(SALESLIST2);

        SALESLIST3.addAll(salesModel.getTop10ProductsFromDatabase("Trousers"));
        ProductNameColumn3.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        TotalQuantitySoldColumn3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table3.setItems(SALESLIST3);

    }

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
    public void goBack(ActionEvent event) throws Exception {

        windows("src/main/resources/com/example/adelaidepremium/fxml/ReportsPage.fxml", "Reports", event);
    }
}
