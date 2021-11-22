package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Customer;
import com.example.adelaidepremium.model.CustomerModel;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDemographicReportController implements Initializable {

    private CustomerModel customerModel;

    @FXML
    private StackedBarChart<String,Number> stackedBarChart1, stackedBarChart2;

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerModel = new CustomerModel();

        loadCharts();
    }
    private void loadCharts(){
        // bar chart
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();
        XYChart.Series series5 = new XYChart.Series();
        XYChart.Series series6 = new XYChart.Series();
        XYChart.Series series7 = new XYChart.Series();
        XYChart.Series series8 = new XYChart.Series();

        for (Customer c : customerModel.getDemographicTablesFromDatabase(1)) {
            String name = c.getPType() + " " + c.getPName();
            switch(c.getGender()){
                case "Agender":
                    series1.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Bigender":
                    series2.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Female":
                    series3.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Genderfluid":
                    series4.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Genderqueer":
                    series5.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Male":
                    series6.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Non-binary":
                    series7.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
                case "Polygender":
                    series8.getData().add(new XYChart.Data(name, c.getPQuantity()));
                    break;
            }
        }

        series1.setName("Agender");
        series2.setName("Bigender");
        series3.setName("Female");
        series4.setName("Genderfluid");
        series5.setName("Genderqueer");
        series6.setName("Male");
        series7.setName("Non-binary");
        series8.setName("Polygender");
        stackedBarChart1.setTitle("Customer Gender vs Most Purchased Product vs Quantity");
        stackedBarChart1.getXAxis().setLabel("Most Purchased Product");
        stackedBarChart1.getYAxis().setLabel("Quantity");
        stackedBarChart1.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8);

        XYChart.Series series9 = new XYChart.Series();
        XYChart.Series series10 = new XYChart.Series();
        XYChart.Series series11 = new XYChart.Series();
        XYChart.Series series12 = new XYChart.Series();

        for (Customer c : customerModel.getDemographicTablesFromDatabase(2)) {
            String name = c.getPType() + " " + c.getPName();
            int age = c.getAge();

            if(age >= 20 && age < 40){
                series9.getData().add(new XYChart.Data(name, c.getPQuantity()));
            }
            else if(age >= 40 && age < 60){
                series10.getData().add(new XYChart.Data(name, c.getPQuantity()));
            }
            else if(age >= 60 && age < 80){
                series11.getData().add(new XYChart.Data(name, c.getPQuantity()));
            }
            else {
                series12.getData().add(new XYChart.Data(name, c.getPQuantity()));
            }
        }

        series9.setName("20 to 40");
        series10.setName("40 to 60");
        series11.setName("60 to 80");
        series12.setName(">=80");
        stackedBarChart2.setTitle("Customer Age vs Most Purchased Product vs Quantity");
        stackedBarChart2.getXAxis().setLabel("Most Purchased Product");
        stackedBarChart2.getYAxis().setLabel("Quantity");
        stackedBarChart2.getData().addAll(series9, series10, series11, series12);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(Customer c : customerModel.getDemographicTablesFromDatabase(3)){
            pieChartData.add(new PieChart.Data(c.getState(), c.getPQuantity()));
        }
        pieChart.setTitle("States Sales Distribution");
        pieChart.getData().addAll(pieChartData);
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
