package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateStockQuantityController implements Initializable {
  @FXML
  TextField productNameField,
      productTypeField,
      productSizeField,
      productColourField,
      productPriceField,
      productQuantityField,
      productDescriptionField,
      productID2Field,
      productQuantity2Field;
  @FXML Button addProductButton, addNewStockQuantityButton;

  @FXML private ObservableList<Product> PRODUCTLIST;
  private ProductModel productModel;

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    PRODUCTLIST = FXCollections.observableArrayList();
    productModel = new ProductModel();

    loadData();
  }

  private void loadData() {
    if (!PRODUCTLIST.isEmpty()) {
      PRODUCTLIST.clear();
    }
    PRODUCTLIST.addAll(productModel.getProducts());
  }

  private int findMaxID() {
    int max = 0;
    for (Product product : PRODUCTLIST) {
      if (product.getId() > max) {
        max = product.getId();
      }
    }
    return max;
  }

  @FXML
  protected void onAddProductButtonClick() {
    try {
      if (validateNewProductInput()) {

        Product product = new Product();
        product.setId(findMaxID() + 1);
        product.setName(productNameField.getText());
        product.setType(productTypeField.getText());
        product.setSize(productSizeField.getText());
        product.setColour(productColourField.getText());
        product.setPrice(Integer.parseInt(productPriceField.getText()));
        product.setQuantity(Integer.parseInt(productQuantityField.getText()));
        product.setDescription(productDescriptionField.getText());

        productModel.addNewProduct(product);
        PRODUCTLIST.clear();
        PRODUCTLIST.addAll(productModel.getProducts());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Product Added");
        alert.setContentText("Product has been added successfully!");
        alert.showAndWait();
      }
    } catch (NumberFormatException ex) {
      System.out.println(ex.getMessage() + ", please enter appropriate values");
    }
  }

  @FXML
  protected void onAddQuantityButtonClick() {
    try {
      if (validateUpdateQuantityInput()) {

        productModel.increaseProduct(
            Integer.parseInt(productID2Field.getText()),
            Integer.parseInt(productQuantity2Field.getText()));

        PRODUCTLIST.clear();
        PRODUCTLIST.addAll(productModel.getProducts());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("New Stock Quantity Added");
        alert.setContentText(
            "The new stock quantity has been added for the specified product successfully!");
        alert.showAndWait();
      }
    } catch (NumberFormatException ex) {
      System.out.println(ex.getMessage() + ", please enter appropriate values");
    }
  }

  private boolean validateNewProductInput() {

    String errorMessage = "";

    if (productNameField.getText() == null || productNameField.getText().length() == 0) {
      errorMessage += "Invalid product name!\n";
    }

    if (productTypeField.getText() == null || productTypeField.getText().length() == 0) {
      errorMessage += "Invalid product type!\n";
    }

    if (productSizeField.getText() == null || productSizeField.getText().length() == 0) {
      errorMessage += "Invalid product size!\n";
    }

    if (productColourField.getText() == null || productColourField.getText().length() == 0) {
      errorMessage += "Invalid product colour!\n";
    }

    if (productPriceField.getText() == null || productPriceField.getText().length() == 0) {
      errorMessage += "Invalid product price!\n";
    }

    if (!onlyDigits(productPriceField.getText())) {
      errorMessage += "Product price is supposed to be numeric!\n";
    }

    if (productQuantityField.getText() == null || productQuantityField.getText().length() == 0) {
      errorMessage += "Invalid product quantity!\n";
    }

    if (!onlyDigits(productQuantityField.getText())) {
      errorMessage += "Product quantity is supposed to be numeric!\n";
    }

    if (productDescriptionField.getText() == null
        || productDescriptionField.getText().length() == 0) {
      errorMessage += "Invalid product description!\n";
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid Fields");
      alert.setHeaderText("Please correct invalid fields");
      alert.setContentText(errorMessage);
      alert.showAndWait();

      return false;
    }
  }

  private boolean validateUpdateQuantityInput() {

    String errorMessage = "";

    if (!onlyDigits(productID2Field.getText())) {
      errorMessage += "Product ID is supposed to be numeric!\n";
    }

    if (productID2Field.getText() == null || productID2Field.getText().length() == 0) {
      errorMessage += "Invalid product ID!\n";
    }

    int maxID = findMaxID();
    if (Integer.parseInt(productID2Field.getText()) > maxID) {
      errorMessage += "Product ID does not exist!\n";
    }

    if (productQuantity2Field.getText() == null || productQuantity2Field.getText().length() == 0) {
      errorMessage += "Invalid product quantity!\n";
    }

    if (!onlyDigits(productQuantity2Field.getText())) {
      errorMessage += "Product quantity is supposed to be numeric!\n";
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid Fields");
      alert.setHeaderText("Please correct invalid fields");
      alert.setContentText(errorMessage);
      alert.showAndWait();

      return false;
    }
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

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/MainMenu.fxml",
        "Adelaide Premium",
        event);
  }
}
