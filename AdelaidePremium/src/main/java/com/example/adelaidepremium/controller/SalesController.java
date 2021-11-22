package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Customer;
import com.example.adelaidepremium.entity.Order;
import com.example.adelaidepremium.entity.Product;
import com.example.adelaidepremium.entity.Sales;
import com.example.adelaidepremium.interfaces.ProductInterface;
import com.example.adelaidepremium.model.CustomerModel;
import com.example.adelaidepremium.model.OrderModel;
import com.example.adelaidepremium.model.ProductModel;
import com.example.adelaidepremium.model.SalesModel;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.ResourceBundle;

public class SalesController implements Initializable, ProductInterface {

    @FXML
    private TableView<Product> productTableView, productTableView1;

    @FXML
    private TableColumn<Product, Integer> idColumn1, priceColumn1;
    @FXML
    private TableColumn<Product, String> typeColumn1, nameColumn1, sizeColumn1, colourColumn1;


    @FXML
    private TableColumn<Product, Integer> idSearchColumn, priceSearchColumn, quantitySearchColumn;
    @FXML
    private TableColumn<Product, String> nameSearchColumn, colourSearchColumn, sizeSearchColumn;

    @FXML
    private TextField searchField, searchField2;

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, String> customerNameColumn, customerCityColumn;

    @FXML
    private TableColumn<Customer, Integer> customerIDColumn, customerZipColumn;

    @FXML
    private ObservableList<Product> PRODUCTLIST;
    private ObservableList<Product> PRODUCTLIST_DISCOUNTED;
    private ObservableList<Product> PURCHASEDLIST;
    private ProductModel productModel;

    @FXML
    private ObservableList<Customer> CUSTOMERLIST;
    private CustomerModel customerModel;

    @FXML
    private ObservableList<Order> ORDERLIST;
    private OrderModel orderModel;

    @FXML
    private ObservableList<Sales> SALESLIST;
    private SalesModel salesModel;

    @FXML
    private TextField customerIDField,productIDField, quantityField, paymentField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PRODUCTLIST = FXCollections.observableArrayList();
        PURCHASEDLIST = FXCollections.observableArrayList();
        productModel = new ProductModel();

        CUSTOMERLIST = FXCollections.observableArrayList();
        customerModel = new CustomerModel();

        ORDERLIST = FXCollections.observableArrayList();
        orderModel = new OrderModel();

        SALESLIST = FXCollections.observableArrayList();
        salesModel = new SalesModel();

        loadData();

        idSearchColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameSearchColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeSearchColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        colourSearchColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
        priceSearchColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantitySearchColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        filterProductsData();

        idColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn1.setCellValueFactory(new PropertyValueFactory<>("type"));
        nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn1.setCellValueFactory(new PropertyValueFactory<>("size"));
        colourColumn1.setCellValueFactory(new PropertyValueFactory<>("colour"));
        priceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));

        displayDiscountProducts();

        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerZipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        customerCityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        filterCustomersData();
    }

    private void loadData() {
        if (!PRODUCTLIST.isEmpty()) {
            PRODUCTLIST.clear();
        }
        PRODUCTLIST.addAll(productModel.getProducts());
        PRODUCTLIST_DISCOUNTED = SharedRandomCatalogueController.generateDiscountedCatalogue(PRODUCTLIST);

        if (!CUSTOMERLIST.isEmpty()) {
            CUSTOMERLIST.clear();
        }
        CUSTOMERLIST.addAll(customerModel.getCustomers());

        if (!ORDERLIST.isEmpty()) {
            ORDERLIST.clear();
        }
        ORDERLIST.addAll(orderModel.getOrders());

        if (!SALESLIST.isEmpty()) {
            SALESLIST.clear();
        }
        SALESLIST.addAll(salesModel.getSales());
    }

    private void filterProductsData() {

        FilteredList<Product> searchedData = new FilteredList<>(PRODUCTLIST, e -> true);

        searchField.setOnKeyReleased(s -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(product -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (product.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (onlyDigits(lowerCaseFilter)) {
                        if(product.getId() == Integer.parseInt(lowerCaseFilter)){
                            return true;
                        }
                    }
                    return false;
                });
            });

            SortedList<Product> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());

            productTableView.setRowFactory(t -> new TableRow<>() {

                @Override
                protected void updateItem(Product product, boolean empty) {
                    super.updateItem(product, empty);
                    styleProperty().unbind();
                    if (empty) {
                        setStyle("");
                    } else if(product.getQuantity()> 0) {
                        this.setStyle("-fx-background-color: green;");
                    }
                }

            });

            productTableView.setItems(sortedData);
        });

    }

    private void filterCustomersData() {

        FilteredList<Customer> searchedCustomerData = new FilteredList<>(CUSTOMERLIST, e -> true);

        searchField2.setOnKeyReleased(s -> {
            searchField2.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedCustomerData.setPredicate(customer -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Customer> sortedData = new SortedList<>(searchedCustomerData);
            sortedData.comparatorProperty().bind(customerTableView.comparatorProperty());

            customerTableView.setItems(sortedData);
        });

    }

    private static boolean onlyDigits(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    private void displayDiscountProducts() {

        FilteredList<Product> filteredData = new FilteredList<>(
                PRODUCTLIST_DISCOUNTED,
                product -> PRODUCTLIST_DISCOUNTED.indexOf(product) < 15
        );

        productTableView1.setItems(filteredData);
    }

    private int findProductsMaxID() {
        int max = 0;
        for (Product product : PRODUCTLIST) {
            if (product.getId() > max) {
                max = product.getId();
            }
        }
        return max;
    }

    private int findCustomersMaxID() {
        int max = 0;
        for (Customer customer : CUSTOMERLIST) {
            if (customer.getId() > max) {
                max = customer.getId();
            }
        }
        return max;
    }

    private int findOrdersMaxID() {
        int max = 0;
        for (Order order : ORDERLIST) {
            if (order.getId() > max) {
                max = order.getId();
            }
        }
        return max;
    }

    private int findSalesMaxID() {
        int max = 0;
        for (Sales sales : SALESLIST) {
            if (sales.getId() > max) {
                max = sales.getId();
            }
        }
        return max;
    }

    @FXML
    protected void verifyCustomerIDButtonClick(){
        try {
            if (validateCustomerInput()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText("Customer Data Exists");
                alert.setContentText("Customer data exists in the database!");
                alert.showAndWait();
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + ", please enter appropriate values");
        }
    }

    @FXML
    protected void addProductButtonClick(){
        try {
            if (validateProductInput()) {

                productModel.decreaseProduct(
                        Integer.parseInt(productIDField.getText()),
                        Integer.parseInt(quantityField.getText()));

                Product p1 = productModel.getProductById(Integer.parseInt(productIDField.getText()));

                for(Product p2 : PRODUCTLIST){
                    if(p2.getId() == p1.getId()){
                        p1.setPrice(p2.getPrice());
                    }
                }

                p1.setQuantity(Integer.parseInt(quantityField.getText()));

                PRODUCTLIST.clear();
                PRODUCTLIST.addAll(productModel.getProducts());

                PURCHASEDLIST.add(p1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText("Product has been added");
                alert.setContentText(
                        "The product has been added to the cart successfully!");
                alert.showAndWait();
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + ", please enter appropriate values");
        }
    }

    @FXML
    protected void confirmOrderButtonClick(){
        int product_total, order_total = 0;
        int order_id, sales_id;
        try {
            if (validateOrderInput()) {
                order_id = findOrdersMaxID();
                sales_id = findSalesMaxID();
                boolean backdated = false;
                StringBuilder outOfStockProducts = new StringBuilder();
                String deliveryMsg = "";
                Sales s = new Sales();

                for(Product p1 : PURCHASEDLIST){
                    for(Product p2: PRODUCTLIST){
                        if(p1.getId() == p2.getId() && p2.getQuantity() <= 0){
                            backdated = true;
                            deliveryMsg = "\n\nSome items have are currently out of stock but the stocks will be refreshed soon.";
                            outOfStockProducts.append("\n Product ID: " + p1.getId() + " ,Product Name: "+ p1.getName());
                        }
                    }
                }
                String[] dates = getDates(backdated);

                Order o = new Order();
                order_id +=1 ;
                o.setId(order_id);
                o.setCustomerId(Integer.parseInt(customerIDField.getText()));
                o.setPayment(Integer.parseInt(paymentField.getText()));
                o.setDate(dates[0]);
                o.setDeliveryDate(dates[1]);
                orderModel.insertOrder(o);

                for (Product p : PURCHASEDLIST){
                    sales_id+=1;
                    s.setId(sales_id);

                    s.setOrderId(order_id);
                    s.setProductId(p.getId());
                    s.setPrice(p.getPrice());
                    s.setQuantity(p.getQuantity());

                    product_total = p.getPrice() * p.getQuantity();
                    s.setTotalPrice(product_total);
                    order_total += product_total;
                    salesModel.insertSales(s);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText("Order has been placed");
                alert.setContentText(
                        "The order has been placed successfully! \n\nOrder Tracking ID: " + order_id + "\nOrder Total (AUD): " + order_total +  deliveryMsg + outOfStockProducts +"\nEstimated Delivery: "+ dates[1]);
                alert.showAndWait();

            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + ", please enter appropriate values");
        }
    }

    private boolean validateCustomerInput(){
        String errorMessage = "";

        int maxID = findCustomersMaxID();

        if (customerIDField.getText() == null || customerIDField.getText().length() == 0) {
            errorMessage += "Invalid customer ID!\n";
        }

        if (!onlyDigits(customerIDField.getText())) {
            errorMessage += "Customer ID is supposed to be numeric!\n";
        }

        if (Integer.parseInt(customerIDField.getText()) > maxID) {
            errorMessage += "Customer ID does not exist, ask customer to register!\n";
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

    private boolean validateProductInput(){
        String errorMessage = "";

        if (productIDField.getText() == null || productIDField.getText().length() == 0) {
            errorMessage += "Invalid product ID!\n";
        }

        if (!onlyDigits(productIDField.getText())) {
            errorMessage += "Product ID is supposed to be numeric!\n";
        }

        int maxID = findProductsMaxID();
        if (Integer.parseInt(productIDField.getText()) > maxID) {
            errorMessage += "Product ID does not exist!\n";
        }

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "Invalid product quantity!\n";
        }

        if (!onlyDigits(quantityField.getText())) {
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

    private boolean validateOrderInput(){
        String errorMessage = "";

        validateCustomerInput();

        if(PURCHASEDLIST.isEmpty()){
            errorMessage += "No products have been added!\n";
        }

        if (paymentField.getText() == null || paymentField.getText().length() == 0) {
            errorMessage += "Invalid payment!\n";
        }

        if (!onlyDigits(paymentField.getText())) {
            errorMessage += "Payment is supposed to be numeric!\n";
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

    private String[] getDates(boolean isSimulated){

        LocalDate order_date, delivery_date;
        String dates[] = new String[2];

        //Getting the current Date value
        order_date = LocalDate.now();

        if(isSimulated){
            //Adding random number of weeks to the current date
            Random r = new Random();
            delivery_date = order_date.plus(r.nextInt(3) + 1, ChronoUnit.WEEKS);
        }
        else{
            delivery_date = order_date;
        }

        dates[0] = order_date.toString();
        dates[1] = delivery_date.toString();

        return dates;
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
