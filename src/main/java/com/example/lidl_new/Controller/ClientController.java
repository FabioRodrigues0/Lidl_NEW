package com.example.lidl_new.Controller;

import com.example.lidl_new.Classes.Invoice;
import com.example.lidl_new.Classes.InvoiceProduct;
import com.example.lidl_new.Model.CardModel;
import com.example.lidl_new.Model.ClientModel;
import com.example.lidl_new.Model.InvoiceModel;
import com.example.lidl_new.Model.InvoiceProductModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientController implements Initializable {
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
  /*
   * Carregar Tabela das faturas do cliente
   */
  @FXML
  public TableView<Invoice> tbInvoice;
  @FXML
  public TableColumn<Invoice, Integer> colInvoiceID;
  @FXML
  public TableColumn<Invoice, Float> colInvoiceTotal;
  @FXML
  public TableColumn<Invoice, String> colInvoiceDate;
  /*
   * Carregar Tabela dos Produtos na fatura do cliente
   */
  @FXML
  public TableView<InvoiceProduct> tbtbInvoiceProducts;
  @FXML
  public TableColumn<InvoiceProduct, String> colInvoiceNameProduct;
  @FXML
  public TableColumn<InvoiceProduct, Float> colInvoiceQT;
  @FXML
  public TableColumn<InvoiceProduct, Float> colInvoicePrice;
  @FXML
  public TextField txtClientID;
  @FXML
  public Button btSearchInvoices;
  @FXML
  public TextField clientPoints;

  InvoiceModel model = new InvoiceModel(0);
  InvoiceProductModel modelProduct = new InvoiceProductModel();
  CardModel c = new CardModel();

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
  }

  //------------------------------------------- Listar Faturas -----------------------------------------------
  /*
   * Lista os produtos na fatura selecionada
   */
  @FXML
  public void onListProductInvoices () {
    Invoice invoiceSelected = tbInvoice.getSelectionModel().getSelectedItem();
    int id = invoiceSelected.getId();

    modelProduct.ListOfProductsInvoices(id);

    colInvoiceNameProduct.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("productName"));
    colInvoiceQT.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, Float>("quantity"));
    colInvoicePrice.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, Float>("productPrice"));

    tbtbInvoiceProducts.setItems(modelProduct.ListOfProductsInvoices(id));
  }

  /*
   * Lista as faturas do cliente indicado
   */
  @FXML
  public void onSearchInvoices () {
    int client = Integer.parseInt(txtClientID.getText());

    colInvoiceID.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("id"));
    colInvoiceTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Float>("total_price"));
    colInvoiceDate.setCellValueFactory(new PropertyValueFactory<Invoice, String>("date"));
    int points = c.getPoints(client);
    clientPoints.setText(String.valueOf(points));
    tbInvoice.setItems(model.getInvoiceModel(client));
  }

  //------------------------------------------- initialize -----------------------------------------------
  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    onListInvoices();
  }

  /*
   * Lista todas as faturas
   */
  @FXML
  public void onListInvoices () {
    colInvoiceID.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("id"));
    colInvoiceTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Float>("total_price"));
    colInvoiceDate.setCellValueFactory(new PropertyValueFactory<Invoice, String>("date"));

    tbInvoice.setItems(model.getInvoiceModel(0));
  }
}
