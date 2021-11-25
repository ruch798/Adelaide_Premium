package com.example.adelaidepremium;

import javafx.stage.Stage;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

/***
 * Database Connectivity: use a relational database through JDBC for managing all the data
 */
public class DatabaseConnection {

  private static final String connectionStr = "jdbc:sqlite:adelaide_premium.db";
  private static Stage stage;
  private static Connection connection = null;

  /***
   * connecting to database
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void database_connect() throws SQLException, ClassNotFoundException {
    try {
      connection = DriverManager.getConnection(connectionStr);
    } catch (SQLException e) {
      System.out.println("Failed to connect!");
      throw e;
    }
  }

  /***
   * disconnecting from the database
   * @throws SQLException
   */
  public static void database_disconnect() throws SQLException {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /***
   * for implementing insert, update and delete statements
   * @param sqlStatement
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void database_executeQuery(String sqlStatement)
      throws SQLException, ClassNotFoundException {
    Statement statement = null;
    try {
      database_connect();
      statement = connection.createStatement();
      statement.executeUpdate(sqlStatement);
    } catch (SQLException e) {
      System.out.println("Exception thrown in database_executeQuery " + e);
      e.printStackTrace();
      throw e;

    } finally {
      if (statement != null) {
        statement.close();
      }
      database_disconnect();
    }
  }

  /***
   * for querying the database
   * @param sqlQuery
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static ResultSet database_execute(String sqlQuery)
      throws ClassNotFoundException, SQLException {
    Statement statement = null;
    ResultSet rs = null;
    CachedRowSet crs = null;

    try {
      database_connect();
      statement = connection.createStatement();
      rs = statement.executeQuery(sqlQuery);
      crs = RowSetProvider.newFactory().createCachedRowSet();
      crs.populate(rs);

    } catch (SQLException e) {
      System.out.println("Exception thrown in database_execute " + e);
      throw e;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (statement != null) {
        statement.close();
      }
      database_disconnect();
    }
    return crs;
  }

  /***
   * getter method
   * @return
   */
  public static Stage getStage() {
    return stage;
  }

  /***
   * setter method
   * @param stage
   */
  public static void setStage(Stage stage) {
    DatabaseConnection.stage = stage;
  }
}
