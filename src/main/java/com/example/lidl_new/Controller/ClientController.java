package com.example.lidl_new.Controller;

import com.example.lidl_new.Model.ClientModel;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ClientController {
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

  public ClientController () {

  }

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
    //MainAppController.loadUI("Home");
  }
}
