package com.example.lidl_new.Controller;


import com.example.lidl_new.MainApp;
import com.example.lidl_new.Model.ClientModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainAppController implements Initializable {
  /*
   *--------------------------------------TABS--------------------------------------------------------
   */
  @FXML // fx:id="MainBorderPane"
  public BorderPane MainBorderPane;
  @FXML // fx:id="tbAbout"
  public Button tbAbout; // Value injected by FXMLLoader
  @FXML // fx:id="tbClient"
  public Button tbClient; // Value injected by FXMLLoader
  @FXML // fx:id="tbHome"
  public Button tbHome; // Value injected by FXMLLoader
  @FXML // fx:id="tbProduct"
  public Button tbProduct; // Value injected by FXMLLoader
  @FXML
  public VBox MainContent;
  @FXML
  public BorderPane loginPane;
  @FXML
  public StackPane MainPane;
  /*
   * Carregar Caixas de Texto e Botões da Scene de login
   */
  @FXML
  public Button btLogin;
  @FXML
  public Button btCancel;
  @FXML
  public TextField lLogin;
  @FXML
  public PasswordField lPassword;
  @FXML
  public Label txtcheckLogin;
  @FXML
  public Button btLogout;

  ClientModel client = new ClientModel();
  ClientController clientController = new ClientController();

  public MainAppController () {

  }

  /*
   *--------------------------------------TABS--------------------------------------------------------
   */
  /*
   *Funções que ao clicar no botão muda chama a funçao que muda a tab
   */
  @FXML
  public void tabHome (ActionEvent event) throws SQLException, IOException {
    loadUI("Home");
  }

  /*
   *--------------------------------------LOAD TABS--------------------------------------------------------
   */
  /*
   *Função que que muda a tab
   */
  public void loadUI (String ui) throws SQLException, IOException {
    Parent root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("Views/" + ui + ".fxml"));

      root = loader.load();
    } catch (IOException ex) {
      Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
    }
    MainBorderPane.setCenter(root);
  }

  @FXML
  public void tabClient (ActionEvent event) throws SQLException, IOException {
    loadUI("Client");
  }

  @FXML
  public void tabProduct (ActionEvent event) throws SQLException, IOException {
    loadUI("Product");
  }

  @FXML
  public void tabAbout (ActionEvent event) throws SQLException, IOException {
    loadUI("About");
  }

  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
  }

  @FXML
  public void onCheckLogin (ActionEvent event) throws IOException, SQLException {
    String login = lLogin.getText();
    String password = lPassword.getText();

    String result = client.CheckLogin(login, password);
    if (result.contains("login")) {
      paneController("login");
    } else {
      txtcheckLogin.setText("Erro no Login, verifique o Login");
    }
  }

  @FXML
  public void paneController (String txt) {
    if (txt.contains("login")) {
      loginPane.setVisible(false);
      MainBorderPane.setVisible(true);
    } else if (txt.contains("logout")) {
      loginPane.setVisible(true);
      MainBorderPane.setVisible(false);
    }
  }

  @FXML
  public void createClient () throws IOException {
    clientController.onAddClient();
  }

  @FXML
  public void logout () {
    paneController("logout");
  }
}