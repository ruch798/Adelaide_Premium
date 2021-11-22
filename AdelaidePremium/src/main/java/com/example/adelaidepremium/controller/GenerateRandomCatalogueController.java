package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GenerateRandomCatalogueController implements Initializable {

    @FXML
    private TableColumn<Product, Integer> idColumn1, priceColumn1;
    @FXML
    private TableColumn<Product, String> typeColumn1, nameColumn1, sizeColumn1, colourColumn1;

    @FXML
    private TableView<Product> productTableView1;

    @FXML
    private ObservableList<Product> PRODUCTLIST;
    private ObservableList<Product> PRODUCTLIST_DISCOUNTED;

    private ProductModel productModel;

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

    private void loadData(){
        if (!PRODUCTLIST.isEmpty()) {
            PRODUCTLIST.clear();
        }
        PRODUCTLIST.addAll(productModel.getProducts());

        PRODUCTLIST_DISCOUNTED = SharedRandomCatalogueController.generateDiscountedCatalogue(PRODUCTLIST);
    }

    private void displayDiscountProducts() {

        FilteredList<Product> filteredData = new FilteredList<>(
                PRODUCTLIST_DISCOUNTED,
                product -> PRODUCTLIST_DISCOUNTED.indexOf(product) < 15
        );

        productTableView1.setItems(filteredData);
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

        windows("src/main/resources/com/example/adelaidepremium/fxml/MainMenu.fxml", "Adelaide Premium", event);
    }
}
