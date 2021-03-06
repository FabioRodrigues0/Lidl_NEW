package com.example.lidl_new;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
  public static void main (String[] args) {
    launch();
  }

  @Override
  public void start (Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Views/MainApp.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
    stage.setTitle("LIDL");
    stage.setScene(scene);
    stage.show();
  }

}