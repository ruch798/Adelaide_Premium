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

/***
 * Main Controller
 */
public class MainController {

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
   * navigates to browse catalogue window
   * @param event
   * @throws Exception
   */
  @FXML
  public void browseCatalogueAction(ActionEvent event) throws Exception {

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/BrowseCatalogue.fxml",
        "Browse Catalogue",
        event);
  }

  /***
   * navigates to generate random catalogue window
   * @param event
   * @throws Exception
   */
  @FXML
  public void generateRandomCatalogueAction(ActionEvent event) throws Exception {

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/GenerateRandomCatalogue.fxml",
        "Generate Random Catalogue",
        event);
  }

  /***
   * navigates to sales window
   * @param event
   * @throws Exception
   */
  @FXML
  public void salesAction(ActionEvent event) throws Exception {

    windows("src/main/resources/com/example/adelaidepremium/fxml/Sales.fxml", "Place Order", event);
  }

  /***
   * navigates to generate reports window
   * @param event
   * @throws Exception
   */
  @FXML
  public void reportsAction(ActionEvent event) throws Exception {

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/ReportsPage.fxml", "Reports", event);
  }

  /***
   * navigates to update Stock Quantity window
   * @param event
   * @throws Exception
   */
  @FXML
  public void updateStockQuantityAction(ActionEvent event) throws Exception {

    windows(
        "src/main/resources/com/example/adelaidepremium/fxml/UpdateStockQuantity.fxml",
        "Update Stock Quantity",
        event);
  }
}
