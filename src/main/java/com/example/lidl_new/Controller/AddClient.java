package com.example.lidl_new.Controller;

import com.example.lidl_new.MainApp;
import com.example.lidl_new.Model.ClientModel;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddClient {
  /*
   * Carregar Caixas de Texto e Botões da Scene de Registar Novo Cliente
   */
  @FXML
  public PasswordField cPasswordClient;
  @FXML
  public TextField cEmailClient;
  @FXML
  public TextField cLocationClient;
  @FXML
  public TextField cFloorClient;
  @FXML
  public TextField cNumberClient;
  @FXML
  public TextField cAddressClient;
  @FXML
  public TextField cNIFClient;
  @FXML
  public TextField cCCClient;
  @FXML
  public TextField cContactClient;
  @FXML
  public TextField cNameClient;
  @FXML
  public Button cClient;
  @FXML
  public Button cCancelClient;

  //------------------------------------------- Criar Clinte -----------------------------------------------
  /*
   * Função chamada no clicar do botão de Registar
   * Chama que cria o cliente
   * No fim chama a função de Mudar de Scene
   */
  @FXML
  public void onCreateClient (ActionEvent event) throws IOException, SQLException {
    // !todo talves nao dê
    int nif = Integer.parseInt(cNIFClient.getText());
    int tlf = Integer.parseInt(cContactClient.getText());
    int port = Integer.parseInt(cNumberClient.getText());

    ClientModel.CreateClient(cNameClient.getText(), cCCClient.getText(), nif, tlf, cAddressClient.getText(), port, cFloorClient.getText(), cLocationClient.getText(), cEmailClient.getText(), cPasswordClient.getText());
  }

  //------------------------------------------- JANELA -----------------------------------------------
  /*
   * Cria uma nova janela para a scene
   */
  @FXML
  public void display () throws IOException {
    Stage window = new Stage();

    window.initModality(Modality.WINDOW_MODAL);
    window.setTitle("Criar Cliente");
    window.setMinWidth(250);

    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Views/LoginRegistration.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
    window.setScene(scene);
    window.show();
  }
}
