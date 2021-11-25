package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
import com.example.adelaidepremium.interfaces.ProductInterface;
import com.example.adelaidepremium.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;

/***
 * Generate Random Catalogue Controller: To generate a random catalog (displays a random set of items on a Window with a discount)
 */
public class GenerateRandomCatalogueController implements Initializable, ProductInterface {

  String basePath = Paths.get("").toAbsolutePath() + "/";
  @FXML private TableColumn<Product, Integer> idColumn1, priceColumn1;
  @FXML private TableColumn<Product, String> typeColumn1, nameColumn1, sizeColumn1, colourColumn1;
  @FXML private TableView<Product> productTableView1;
  @FXML private ObservableList<Product> PRODUCTLIST;
  private ObservableList<Product> PRODUCTLIST_DISCOUNTED;
  private ProductModel productModel;

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

    idColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
    typeColumn1.setCellValueFactory(new PropertyValueFactory<>("type"));
    nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
    sizeColumn1.setCellValueFactory(new PropertyValueFactory<>("size"));
    colourColumn1.setCellValueFactory(new PropertyValueFactory<>("colour"));
    priceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));

    displayDiscountProducts();
  }

  /***
   * load data
   */
  private void loadData() {
    if (!PRODUCTLIST.isEmpty()) {
      PRODUCTLIST.clear();
    }
    PRODUCTLIST.addAll(productModel.getProducts());

    PRODUCTLIST_DISCOUNTED =
        SharedRandomCatalogueController.generateDiscountedCatalogue(PRODUCTLIST);
  }

  /***
   * display Discount Products
   */
  private void displayDiscountProducts() {

    FilteredList<Product> filteredData =
        new FilteredList<>(
            PRODUCTLIST_DISCOUNTED, product -> PRODUCTLIST_DISCOUNTED.indexOf(product) < 15);

    productTableView1.setItems(filteredData);
  }

  /***
   * write list to file
   */
  private void writeToFile() {

    FilteredList<Product> filteredData =
        new FilteredList<>(
            PRODUCTLIST_DISCOUNTED, product -> PRODUCTLIST_DISCOUNTED.indexOf(product) < 15);

    try (FileWriter writer =
        new FileWriter(
            basePath + "src/main/resources/com/example/adelaidepremium/mail data/deals.txt"); ) {
      String formatStr = "%-15s %-15s %-20s %-15s %-15s %-15s%n";
      writer.write(
          String.format(
              formatStr,
              "Product ID",
              "Product Type",
              "Product Name",
              "Size",
              "Colour",
              "Price (AUD)"));
      for (Product p : filteredData) {
        writer.write(
            String.format(
                formatStr,
                p.getId(),
                p.getType(),
                p.getName(),
                p.getSize(),
                p.getColour(),
                p.getPrice()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /***
   * send catalogue via mail
   */
  @FXML
  private void onSendCatalogueButtonClick() {
    writeToFile();
    // Recipient's email ID
    String to = "rsbhatia@andrew.cmu.edu";
    // Sender's email ID
    String from = "adelaide.premium7@gmail.com";
    String host = "smtp.gmail.com";

    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    // Authentication
    Session session =
        Session.getInstance(
            properties,
            new javax.mail.Authenticator() {

              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "adelaide2021");
              }
            });

    try {
      MimeMessage message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("\uD83D\uDECD️Discounted Products at Adelaide Premium\uD83D\uDECD️");

      Multipart multipart = new MimeMultipart();
      MimeBodyPart attachmentPart1 = new MimeBodyPart();
      MimeBodyPart attachmentPart2 = new MimeBodyPart();
      MimeBodyPart textPart = new MimeBodyPart();

      try {

//        deals txt file
        File deals =
            new File(
                basePath + "src/main/resources/com/example/adelaidepremium/mail data/deals.txt");
        attachmentPart1.attachFile(deals);

//        image file
        File logo =
            new File(
                basePath
                    + "src/main/resources/com/example/adelaidepremium/mail data/AdelaidePremium.png");
        attachmentPart2.attachFile(logo);

//        email text
        textPart.setText(
            "Dear Customer, \n\nSince you are a member of the inner circle at Adelaide Premium, we'd like to notify you about the current best deals! \n\nPlease find attached the list of discounted products. \n\nHappy Shopping! \n\n-Adelaide Premium");

        multipart.addBodyPart(attachmentPart1);
        multipart.addBodyPart(attachmentPart2);
        multipart.addBodyPart(textPart);

      } catch (IOException e) {

        e.printStackTrace();
      }

      message.setContent(multipart);
      Transport.send(message);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Successful");
      alert.setHeaderText("Mail sent");
      alert.setContentText("Mail sent successfully!");
      alert.showAndWait();
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
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
