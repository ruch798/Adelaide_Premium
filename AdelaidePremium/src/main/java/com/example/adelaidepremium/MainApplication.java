package com.example.adelaidepremium;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/***
 * Main Application to launch the application
 */
public class MainApplication extends Application {
  public static void main(String[] args) {
    launch();
  }

  /***
   * Sets the scene and title and shows the loaded fxml
   * @param stage
   * @throws IOException
   */
  @Override
  public void start(Stage stage) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/MainMenu.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Adelaide Premium");
    stage.setScene(scene);
    stage.show();
  }
}
