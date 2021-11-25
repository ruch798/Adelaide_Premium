package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
import com.example.adelaidepremium.interfaces.ProductInterface;
import com.example.adelaidepremium.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Stocktake Report Controller: To display Stocktake Report (shows each item with description and quantity on hand)
 */
public class StocktakeReportController implements Initializable, ProductInterface {

  @FXML private TableColumn<Product, String> productNameColumn, descriptionColumn;

  @FXML private TableColumn<Product, Integer> totalQuantityColumn;

  @FXML private BarChart<String, Number> stockChart;

  @FXML private TableView<Product> stockTable;

  private ProductModel productModel;

  /**
   * initialization
   *
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    productModel = new ProductModel();

    loadStockCharts();
  }

  /***
   * load stock charts
   */
  private void loadStockCharts() {
    // bar chart
    ObservableList lists = FXCollections.observableArrayList();
    XYChart.Series series1 = new XYChart.Series<>();
    XYChart.Series series2 = new XYChart.Series<>();
    XYChart.Series series3 = new XYChart.Series<>();

    for (Product p : productModel.getProductsStock()) {
      String name = p.getType() + " " + p.getName();
      lists.add(name);
      switch (p.getType()) {
        case "Shirt":
          series1.getData().add(new XYChart.Data(name, p.getQuantity()));
          break;
        case "Jacket":
          series2.getData().add(new XYChart.Data(name, p.getQuantity()));
          break;
        case "Trousers":
          series3.getData().add(new XYChart.Data(name, p.getQuantity()));
          break;
      }
    }

    series1.setName("Shirt");
    series2.setName("Jacket");
    series3.setName("Trousers");
    stockChart.setTitle("Stocktake Report");
    stockChart.getXAxis().setLabel("Product Name");
    stockChart.getYAxis().setLabel("Total Quantity");
    stockChart.getData().addAll(series1, series2, series3);

    //        description table
    if (!PRODUCTLIST.isEmpty()) {
      PRODUCTLIST.clear();
    }

    PRODUCTLIST.addAll(productModel.getProductsStock());
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    totalQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    stockTable.setItems(PRODUCTLIST);
  }

  /***
   * window
   * @param path
   * @param title
   * @param event
   * @throws Exception
   */
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

  /**
   * back button
   *
   * @param event
   * @throws Exception
   */
  @FXML
  public void goBack(ActionEvent event) throws Exception {

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/ReportsPage.fxml", "Reports", event);
  }
}
