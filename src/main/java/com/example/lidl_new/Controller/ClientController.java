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
  public TableView<InvoiceProduct> tbInvoiceProducts;
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
  @FXML
  public Button btCreateClient;

  InvoiceModel invoice = new InvoiceModel(0);
  InvoiceProductModel invoiceProduct = new InvoiceProductModel();
  CardModel card = new CardModel();
  AddClient addClient = new AddClient();

  public ClientController () {

  }

  //------------------------------------------- Listar Faturas -----------------------------------------------
  /*
   * Lista os produtos na fatura selecionada
   */
  @FXML
  public void onListProductInvoices () {
    Invoice invoiceSelected = tbInvoice.getSelectionModel().getSelectedItem();
    int id = invoiceSelected.getId();

    invoiceProduct.ListOfProductsInvoices(id);

    colInvoiceNameProduct.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("productName"));
    colInvoiceQT.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, Float>("quantity"));
    colInvoicePrice.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, Float>("productPrice"));

    tbInvoiceProducts.setItems(invoiceProduct.ListOfProductsInvoices(id));
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
    int points = card.getPoints(client);
    clientPoints.setText(String.valueOf(points));
    tbInvoice.setItems(invoice.getInvoiceModel(client));
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

    tbInvoice.setItems(invoice.getInvoiceModel(0));
  }

  @FXML
  public void onAddClient () throws IOException {
    addClient.display();
  }

}
