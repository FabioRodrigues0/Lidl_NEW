package com.example.lidl_new.Controller;


import com.example.lidl_new.MainApp;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainAppController implements Initializable {
  /*
   *--------------------------------------TABS--------------------------------------------------------
   */
  @FXML // fx:id="MainBorderPane"
  public BorderPane MainBorderPane;
  @FXML // fx:id="tbAbout"
  public Button tbAbout; // Value injected by FXMLLoader
  @FXML // fx:id="tbAddProduct"
  public Button tbAddProduct; // Value injected by FXMLLoader
  @FXML // fx:id="tbClient"
  public Button tbClient; // Value injected by FXMLLoader
  @FXML // fx:id="tbHome"
  public Button tbHome; // Value injected by FXMLLoader
  @FXML // fx:id="tbProduct"
  public Button tbProduct; // Value injected by FXMLLoader
  @FXML // fx:id="tbUpdateProduct"
  public Button tbUpdateProduct; // Value injected by FXMLLoader
  @FXML
  public VBox MainContent;

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
    loadUI("Home", event);
  }

  /*
   *Função que que muda a tab
   */
  public void loadUI (String ui, ActionEvent event) throws SQLException, IOException {
    Parent root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource(ui + ".fxml"));

      root = loader.load();
    } catch (IOException ex) {
      Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
    }
    MainBorderPane.setCenter(root);
  }

  @FXML
  public void tabClient (ActionEvent event) throws SQLException, IOException {
    loadUI("Client", event);
  }

  @FXML
  public void tabProduct (ActionEvent event) throws SQLException, IOException {
    loadUI("Product", event);
  }

  @FXML
  public void tabAddProduct (ActionEvent event) throws SQLException, IOException {
    loadUI("AddProduct", event);
  }

  @FXML
  public void tabUpdateProduct (ActionEvent event) throws SQLException, IOException {
    loadUI("UpdateProduct", event);
  }

  @FXML
  public void tabAbout (ActionEvent event) throws SQLException, IOException {
    loadUI("About", event);
  }

  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {

  }
}