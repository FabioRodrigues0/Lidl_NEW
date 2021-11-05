package com.example.lidl_new.Controller;

import com.example.lidl_new.MainApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateWindow {
  public static Button yesButton;
  public static Button noButton;
  static boolean answer;

  public static boolean display () throws IOException {


    Stage window = new Stage();
    window.initModality(Modality.WINDOW_MODAL);
    window.setTitle("Confirmar");
    window.setMinWidth(250);

    yesButton.setOnAction(event -> {
      answer = true;
      window.close();
    });
    noButton.setOnAction(event -> {
      answer = true;
      window.close();
    });

    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("DeleteWindow.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1250, 840);
    window.setScene(scene);
    window.showAndWait();

    return answer;
  }
}