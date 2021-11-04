package com.example.lidl_new.Controller;

import com.example.lidl_new.Classes.Invoice;
import com.example.lidl_new.Classes.Product;
import com.example.lidl_new.Model.InvoiceModel;
import com.example.lidl_new.Model.ProductModel;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;

import static java.util.Arrays.setAll;

import static com.example.lidl_new.Classes.Category.getCategory;

public class HomeController implements Initializable {
  @FXML
  public TextField sQuantity;
  @FXML
  public TilePane tp_Category;
  @FXML
  public Button btCategory;
  @FXML
  public TilePane tp_Products;
  @FXML
  public Button btProducts;
  @FXML
  public Button btQuantity0;
  @FXML
  public Button btQuantity1;
  @FXML
  public Button btQuantity2;
  @FXML
  public Button btQuantity3;
  @FXML
  public Button btQuantity4;
  @FXML
  public Button btQuantity5;
  @FXML
  public Button btQuantity6;
  @FXML
  public Button btQuantity7;
  @FXML
  public Button btQuantity8;
  @FXML
  public Button btQuantity9;
  @FXML
  public Button btQuantityClear;
  @FXML
  public TableView<Invoice> tbInvoice;
  @FXML
  public TableColumn<Invoice, String> colProduct;
  @FXML
  public TableColumn<Invoice, Integer> colQuantity;
  @FXML
  public TableColumn<Invoice, Double> colPrice;
  @FXML
  public TextField txtclientID;
  @FXML
  public Button btShop;

  ProductModel productModel = new ProductModel();
  InvoiceModel invoiceModel = new InvoiceModel();
  Invoice i = new Invoice();
  ObservableList<Product> product = productModel.getProducts();
  int lastIndex = 0;
  boolean shopFinished;
  private ObservableList<Invoice> invoices = invoiceModel.getInvoices();

  public HomeController () {
  }

  //------------------------------------------- Função ao inciar App -----------------------------------------------
  /*
   * Função cria os botoes das categorias dos produtos basiado nas categorias que existem na base de dados
   */
  @FXML
  public void onListCategories () {
    List<Button> listCategory = new ArrayList<>();//our Collection to hold newly created Buttons
    for (int i = 0; i < getCategory().size(); i++) {
      tp_Category.getChildren().clear();
      btCategory = new Button(getCategory().get(i).getCategoryName());
      btCategory.setMinWidth(145);
      btCategory.setPrefHeight(40);
      btCategory.setWrapText(true);
      // action event
      EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle (ActionEvent e) {
          onBtListProduct(e);
        }
      };

      btCategory.setOnAction(event);
      listCategory.add(btCategory);
    }
    tp_Category.getChildren().addAll(listCategory);
  }

  /*
   * Função cria os botoes dos produtos que existem basiado na categorias clicado
   */
  @FXML
  public void onBtListProduct (ActionEvent event) {
    // !todo WARNING E ma utilização depender do texto do botão
    String nameButton = ((Button) event.getSource()).getText();
    List<Button> listProduct = new ArrayList<Button>();//our Collection to hold newly created Buttons
    List<Product> productListQuantity = product.stream().filter(product -> product.getCategory().equals(nameButton)).collect(Collectors.toList());

    tp_Products.getChildren().clear();
    for (Product value : productListQuantity) {
      btProducts = new Button(value.getProductName());
      btProducts.setPrefWidth(70);
      btProducts.setPrefHeight(80);
      btProducts.setWrapText(true);
      btProducts.setAlignment(Pos.CENTER);
      // action event
      EventHandler<ActionEvent> evt = new EventHandler<ActionEvent>() {
        public void handle (ActionEvent e) {
          onToInvoice(e);
        }
      };

      btProducts.setOnAction(evt);
      listProduct.add(btProducts);
    }
    tp_Products.getChildren().addAll(listProduct);
  }

  @FXML
  public void onToInvoice (ActionEvent event) {
    // !todo WARNING E ma utilização depender do texto do botão
    String nameProduct = ((Button) event.getSource()).getText();
    int productQuantity = Integer.parseInt(sQuantity.getText());
    int idClient = Integer.parseInt(txtclientID.getText());

    Product productIndex = null;
    //este for e para procurar nos produtos o index do produto para pedir o preço
    for (Product product : product) {
      if (product.getProductName().equals(nameProduct)) {
        productIndex = product;
      }
    }
    int index = product.indexOf(productIndex);
    double price = product.get(index).getPrice();

    invoices = FXCollections.observableArrayList();

    invoices.add(new Invoice(nameProduct, idClient, productQuantity, price));

    System.out.println(invoices.size());
    lastIndex++;
    updateProductsToInvoice();
  }

  @FXML
  public void finishShop () {
    shopFinished = false;
  }

  @FXML
  public void updateProductsToInvoice () {
    colProduct.setCellValueFactory(new PropertyValueFactory<Invoice, String>("nameProduct"));
    colQuantity.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("quantity"));
    colPrice.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("price"));

    tbInvoice.setItems(invoices);
  }

  /*
   * Adiciona a quantia a caixa de texto quantidade
   */
  public void productQuantity (ActionEvent event) {
    // !todo WARNING E ma utilização depender do texto do botão
    String numberBT = ((Button) event.getSource()).getText();
    String txtquantity = sQuantity.getText();
    switch (numberBT) {
      case "1" -> sQuantity.setText(txtquantity + "1");
      case "2" -> sQuantity.setText(txtquantity + "2");
      case "3" -> sQuantity.setText(txtquantity + "3");
      case "4" -> sQuantity.setText(txtquantity + "4");
      case "5" -> sQuantity.setText(txtquantity + "5");
      case "6" -> sQuantity.setText(txtquantity + "6");
      case "7" -> sQuantity.setText(txtquantity + "7");
      case "8" -> sQuantity.setText(txtquantity + "8");
      case "9" -> sQuantity.setText(txtquantity + "9");
      case "0" -> sQuantity.setText(txtquantity + "0");
      case "Clear" -> sQuantity.setText("");
    }
  }

  //----------------------------- Função de Iniciar o Controlador -----------------------------------------------
  /*
   * faz load da lista dos produtos assim que o Product.fxml faz load
   * o @FXML e para indicar ao FXML para olhar isto quando faz load ao ficheiro
   */
  @FXML
  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    onListCategories();
  }


}
