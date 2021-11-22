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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class QuarterlySalesReportController implements Initializable {
    private SalesModel salesModel;

    @FXML
    private BarChart<String,Number> quarterlySalesRevenueBarChart, quarterlySalesQuantityBarChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salesModel = new SalesModel();

        loadQuartelySalesChart();
    }
    private void loadQuartelySalesChart(){
        // bar chart

        ObservableList list1 = FXCollections.observableArrayList();
        ObservableList list2 = FXCollections.observableArrayList();
        XYChart.Series series1 = new XYChart.Series<>();
        XYChart.Series series2 = new XYChart.Series<>();


        for (Sales s : salesModel.getSalesForQuarterFromDatabase(1)) {
            series1.getData().add(new XYChart.Data(s.getMonth(), s.getTotalSales()));
            list1.add(s.getMonth());
        }

        series1.setName("Previous Quarter Revenue");
        quarterlySalesRevenueBarChart.setTitle("Previous Quarter Revenue");
        quarterlySalesRevenueBarChart.getXAxis().setLabel("Month");
        quarterlySalesRevenueBarChart.getYAxis().setLabel("Total Revenue");
        quarterlySalesRevenueBarChart.getData().add(series1);

        for (Sales s : salesModel.getSalesForQuarterFromDatabase(2)) {
            series2.getData().add(new XYChart.Data(s.getMonth(), s.getTotalSales()));
            list2.add(s.getMonth());
        }

        series2.setName("Previous Quarter Quantity Sold");
        quarterlySalesQuantityBarChart.setTitle("Previous Quarter Quantity Sold");
        quarterlySalesQuantityBarChart.getXAxis().setLabel("Month");
        quarterlySalesQuantityBarChart.getYAxis().setLabel("Total Quantity Sold");
        quarterlySalesQuantityBarChart.getData().add(series2);
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
