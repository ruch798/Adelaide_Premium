package com.example.adelaidepremium.model;

import com.example.adelaidepremium.DatabaseConnection;
import com.example.adelaidepremium.entity.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesModel{
    private static Sales getSalesObject(ResultSet rsSet)
            throws SQLException, ClassNotFoundException {
        try {
            Sales sales = new Sales();

            while(rsSet.next()){
                sales.setId(rsSet.getInt("sales_id"));
                sales.setOrderId(rsSet.getInt("order_id"));
                sales.setProductId(rsSet.getInt("product_id"));
                sales.setPrice(rsSet.getInt("price_per_unit"));
                sales.setQuantity(rsSet.getInt("quantity"));
                sales.setQuantity(rsSet.getInt("total_price"));
            }

            return sales;
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching data!");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Sales> getSalesObjects(ResultSet rsSet)
            throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Sales> saleslist = FXCollections.observableArrayList();

            while (rsSet.next()) {
                Sales sales = new Sales();
                sales.setId(rsSet.getInt("sales_id"));
                sales.setOrderId(rsSet.getInt("order_id"));
                sales.setProductId(rsSet.getInt("product_id"));
                sales.setPrice(rsSet.getInt("price_per_unit"));
                sales.setQuantity(rsSet.getInt("quantity"));
                sales.setQuantity(rsSet.getInt("total_price"));
                saleslist.add(sales);
            }
            return saleslist;
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching data!");
            e.printStackTrace();
            return null;
        }
    }
    public ObservableList<Sales> getSales(){
        String sql = "select * from sales";
        try {
            ResultSet rs = DatabaseConnection.database_execute(sql);
            ObservableList<Sales> saleslist = getSalesObjects(rs);
            return saleslist;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Sales getSalesId(int salesId){
        String sql = "select * from sales where sales_id = "+salesId+"";
        try {
            ResultSet rs = DatabaseConnection.database_execute(sql);
            Sales sales = getSalesObject(rs);
            return sales;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Record not found");
            return null;
        }
    }
    public ObservableList<Sales> getSalesByCustomerId(int customerId){
        String sql = "select * from sales where customer_id = "+customerId+"";
        try {
            ResultSet rs = DatabaseConnection.database_execute(sql);
            ObservableList<Sales> saleslist = getSalesObjects(rs);
            return saleslist;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Record not found");
            return null;
        }
    }
    public ObservableList<Sales> getSalesByOrderId(int orderId){
        String sql = "select * from sales where order_id = "+orderId+"";
        try {
            ResultSet rs = DatabaseConnection.database_execute(sql);
            ObservableList<Sales> saleslist = getSalesObjects(rs);
            return saleslist;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Record not found");
            return null;
        }
    }
    public ObservableList<Sales> getSalesForQuarterFromDatabase(int n){
        String sql = null;
        if(n == 1){
//            total revenue
            sql = "select strftime('%Y-%m', o.delivery_date) AS sales_month, sum(total_price) as total from sales as s  inner join orders as o on s.order_id = o.order_id group by sales_month order by sales_month desc LIMIT 4 OFFSET 3";
        }
        else if(n==2){
//            total quantity sold
            sql = "select strftime('%Y-%m', o.delivery_date) AS sales_month, sum(quantity) as total from sales as s  inner join orders as o on s.order_id = o.order_id group by sales_month order by sales_month desc LIMIT 4 OFFSET 3";
        }

        try {
            ResultSet rs = DatabaseConnection.database_execute(sql);
            ObservableList<Sales> saleslist = getSalesForQuarter(rs);
            return saleslist;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Record not found");
            return null;
        }
    }

    public ObservableList<Sales> getTop10ProductsFromDatabase(String prodType){
        String sql =
        "SELECT  strftime('%Y-%m', o.delivery_date) AS sales_month, p.product_type, p.product_name, SUM(s.quantity) as sales_quantity FROM sales as s INNER JOIN orders as o ON s.order_id = o.order_id INNER JOIN products as p ON s.product_id = p.product_id WHERE sales_month=\"2021-10\" and product_type = \"" + prodType  + "\" GROUP BY product_name ORDER BY product_type, sales_quantity DESC LIMIT 10";

        try {
            ResultSet rs = DatabaseConnection.database_execute(sql);
            ObservableList<Sales> saleslist = getTop10Products(rs);
            return saleslist;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Record not found");
            return null;
        }
    }

    public void insertSales(Sales sales){
        String sql = "insert into sales values("+sales.getId()+", "+sales.getOrderId()+", "+sales.getProductId()+", "+sales.getPrice()+", "+ sales.getQuantity() + ", " +sales.getTotalPrice()+")";
        System.out.println(sql);
        try {
            DatabaseConnection.database_executeQuery(sql);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Exception occured while inserting data");
        }
    }
    private static ObservableList<Sales> getSalesForQuarter(ResultSet rsSet)
            throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Sales> saleslist = FXCollections.observableArrayList();

            while (rsSet.next()) {
                Sales sales = new Sales(null, 0);
                sales.setMonth(rsSet.getString("sales_month"));
                sales.setTotalSales(rsSet.getInt("total"));
                saleslist.add(sales);
            }
            return saleslist;
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching data!");
            e.printStackTrace();
            return null;
        }
    }

    private static ObservableList<Sales> getTop10Products(ResultSet rsSet)
            throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Sales> saleslist = FXCollections.observableArrayList();

            while (rsSet.next()) {
                Sales sales = new Sales(null, null, 0);

                sales.setProdName(rsSet.getString("product_name"));
                sales.setProdType(rsSet.getString("product_type"));
                sales.setQuantity(rsSet.getInt("sales_quantity"));
                saleslist.add(sales);
            }
            return saleslist;
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching data!");
            e.printStackTrace();
            return null;
        }
    }
}
