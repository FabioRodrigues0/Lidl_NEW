package com.example.lidl_new.Controller;

import com.example.lidl_new.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteWindow implements Initializable {
  @FXML
  public Button yesButton;
  @FXML
  public Button noButton;
  @FXML
  Stage window = new Stage();
  private boolean answer;

  @FXML
  public boolean display () throws IOException {
    window.initModality(Modality.WINDOW_MODAL);
    window.setTitle("Confirmar");
    window.setMinWidth(250);

    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("DeleteWindow.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 200);
    window.setScene(scene);
    window.showAndWait();

    return answer;
  }


  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {

  }

  public void yes (ActionEvent event) {
    System.out.println("Clicked");
    answer = true;
    window.close();
  }

  public void no (ActionEvent event) {
    System.out.println("Clicked");
    answer = false;
    window.close();
  }
}
