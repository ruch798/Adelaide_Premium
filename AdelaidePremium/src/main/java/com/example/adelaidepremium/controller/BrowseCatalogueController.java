package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
import com.example.adelaidepremium.interfaces.ProductInterface;
import com.example.adelaidepremium.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Browse Catalogue Controller: To Browse Items
 */
public class BrowseCatalogueController implements Initializable, ProductInterface {

  @FXML private TableView<Product> productTableView;

  @FXML
  private TableColumn<Product, Integer> idSearchColumn, priceSearchColumn, quantitySearchColumn;
  @FXML private TableColumn<Product, String> nameSearchColumn, colourSearchColumn, sizeSearchColumn;

  @FXML private TextField searchField;

  @FXML private ObservableList<Product> PRODUCTLIST;
  private ProductModel productModel;

  /***
   * check if string only has digits
   * @param str
   * @return whether string only has digits
   */
  private static boolean onlyDigits(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  /***
   * initialize fields
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    PRODUCTLIST = FXCollections.observableArrayList();
    productModel = new ProductModel();

    loadData();

    idSearchColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameSearchColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    sizeSearchColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    colourSearchColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
    priceSearchColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    quantitySearchColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    filterProductsData();
  }

  /***
   * load data into list
   */
  private void loadData() {
    if (!PRODUCTLIST.isEmpty()) {
      PRODUCTLIST.clear();
    }
    PRODUCTLIST.addAll(productModel.getProducts());
  }

  /***
   * filter products data
   */
  private void filterProductsData() {

    FilteredList<Product> searchedData = new FilteredList<>(PRODUCTLIST, e -> true);

    searchField.setOnKeyReleased(
        s -> {
          searchField
              .textProperty()
              .addListener(
                  (observable, oldValue, newValue) -> {
                    searchedData.setPredicate(
                        product -> {
                          if (newValue == null || newValue.isEmpty()) {
                            return true;
                          }
                          String lowerCaseFilter = newValue.toLowerCase();
                          if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                          } else if (product
                              .getDescription()
                              .toLowerCase()
                              .contains(lowerCaseFilter)) {
                            return true;
                          } else if (onlyDigits(lowerCaseFilter)) {
                            if (product.getId() == Integer.parseInt(lowerCaseFilter)) {
                              return true;
                            }
                          }
                          return false;
                        });
                  });

          SortedList<Product> sortedData = new SortedList<>(searchedData);
          sortedData.comparatorProperty().bind(productTableView.comparatorProperty());

          productTableView.setRowFactory(
              t ->
                  new TableRow<>() {

                    @Override
                    protected void updateItem(Product product, boolean empty) {
                      super.updateItem(product, empty);
                      styleProperty().unbind();
                      if (empty) {
                        setStyle("");
                      } else if (product.getQuantity() > 0) {
                        this.setStyle("-fx-background-color: green;");
                      }
                    }
                  });

          productTableView.setItems(sortedData);
        });
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

  /***
   * back button
   * @param event
   * @throws Exception
   */
  @FXML
  public void goBack(ActionEvent event) throws Exception {

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/MainMenu.fxml",
        "Adelaide Premium",
        event);
  }
}
