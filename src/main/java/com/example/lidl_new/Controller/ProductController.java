package com.example.lidl_new.Controller;

import com.example.lidl_new.Classes.Product;
import com.example.lidl_new.Database.Conn;
import com.example.lidl_new.Model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import static com.example.lidl_new.Classes.Brand.getBrand;
import static com.example.lidl_new.Classes.Category.getCategory;
import static com.example.lidl_new.Classes.Tax.getTax;

public class ProductController implements Initializable {
  /*
   * Carregar Caixas de Texto e Botões da tab Adicionar Artigo
   */
  @FXML
  public TextField cNameProduct;
  @FXML
  public TextField cPrice;
  @FXML
  public TextField cStock;
  @FXML
  public TextField cBarcode;
  @FXML
  public ChoiceBox<String> cBrand;
  @FXML
  public ChoiceBox<String> cCategory;
  @FXML
  public ChoiceBox<Integer> cTax;
  @FXML
  public Button cProduct;
  @FXML
  public Button cBtClean;
  /*
   * Carregar Caixas de Texto e Botões da tab Update Artigo
   */
  @FXML
  public TextField uId;
  @FXML
  public TextField uNameProduct;
  @FXML
  public TextField uPrice;
  @FXML
  public TextField uStock;
  @FXML
  public TextField uBarcode;
  @FXML
  public ChoiceBox<String> uBrand;
  @FXML
  public ChoiceBox<String> uCategory;
  @FXML
  public ChoiceBox<Integer> uTax;
  @FXML
  public Button uBtUpdate;
  @FXML
  public Button btUClose;
  /*
   * Carregar Caixas de Texto e Botões da Tab Gestao de Artigos
   */
  @FXML
  public Button btAddProduct;
  @FXML
  public Button btUpdateProduct;
  @FXML
  public Button btDeleteProduct;
  @FXML
  public Button btUpdateList;
  /*
   * tabela dos produtos
   */
  @FXML
  public TableView<Product> tvListProducts;
  @FXML
  public TableColumn<Product, Integer> colId;
  @FXML
  public TableColumn<Product, Integer> colBarcode;
  @FXML
  public TableColumn<Product, String> colProductName;
  @FXML
  public TableColumn<Product, String> colCategory;
  @FXML
  public TableColumn<Product, String> colBrand;
  @FXML
  public TableColumn<Product, Integer> colStock;
  @FXML
  public TableColumn<Product, Float> colPrice;
  @FXML
  public TableColumn<Product, Integer> colTax;
  @FXML
  public HBox tabUpdateProduct;
  @FXML
  public HBox tabAddProduct;
  @FXML
  public HBox tabProduct;


  ProductModel model = new ProductModel();
  MainAppController load = new MainAppController();

  Connection conn;
  String query;
  Statement stmt;
  ResultSet rs;

  public ProductController () {
    this.conn = Conn.con;
    this.query = Conn.query;
    this.stmt = Conn.stmt;
    this.rs = Conn.rs;
  }

  //------------------------------------------- Criar Producto -----------------------------------------------
  /*
   * Função chamada no clicar do botão de Registar
   * Chama que função de criar o produto
   * No fim chama a função de fechar a tab
   */
  @FXML
  public void onCreateProduct (ActionEvent event) throws IOException, SQLException {
    // !todo talves nao dê
    int barcode = Integer.parseInt(cBarcode.getText());
    int stock = Integer.parseInt(cStock.getText());
    int category = 99, brand = 99, tax = 99;
    for (int i = 0; i < 18; i++) {
      if (Objects.equals(getBrand().get(i).getBrandName(), cBrand.getValue())) {
        brand = getBrand().get(i).getId();
      } else if (Objects.equals(getCategory().get(i).getCategoryName(), cCategory.getValue())) {
        category = getCategory().get(i).getId();
      }
      for (int j = 0; j < 3; j++) {
        if (Objects.equals(getTax().get(j).getTaxPercent(), cTax.getValue())) {
          tax = getTax().get(j).getId();
        }
      }
    }
    float price = Float.parseFloat(cPrice.getText());

    ProductModel.createProduct(barcode, cNameProduct.getText(), category, brand, stock, price, tax);
  }

  //------------------------------------------- Update Producto -----------------------------------------------
  /*
   * Função chamada no clicar do botão de Update
   * Chama que função de dar update ao produto
   */
  @FXML
  public void setUpdateProduct (ActionEvent event) throws IOException, SQLException {
    int id = Integer.parseInt(uId.getText());
    int barcode = Integer.parseInt(uBarcode.getText());
    int stock = Integer.parseInt(uStock.getText());
    int category = 99, brand = 99, tax = 99;
    for (int i = 0; i < 18; i++) {
      if (Objects.equals(getBrand().get(i).getBrandName(), uBrand.getValue())) {
        brand = getBrand().get(i).getId();
      } else if (Objects.equals(getCategory().get(i).getCategoryName(), uCategory.getValue())) {
        category = getCategory().get(i).getId();
      }
      for (int j = 0; j < 3; j++) {
        if (Objects.equals(getTax().get(j).getTaxPercent(), uTax.getValue())) {
          tax = getTax().get(j).getId();
        }
      }
    }
    float price = Float.parseFloat(uPrice.getText());

    ProductModel.updateProduct(id, barcode, uNameProduct.getText(), category, brand, stock, price, tax);
  }

/*  @FXML
  public void test () {
    // action event
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle (ActionEvent e) {
        Product productSelected = tvListProducts.getSelectionModel().getSelectedItem();
        onUpdateProduct(e, productSelected);
      }
    };
    btUpdateProduct.setOnAction(event);
  }*/

  /*
   * e chamada atravez da lista de produtos selecionando qual produto se quer dar update
   */
  @FXML
  public void onUpdateProduct (ActionEvent e, Product productSelected) {
    for (int i = 0; i < getBrand().size(); i++) {
      uBrand.getItems().addAll(getBrand().get(i).getBrandName());
    }
    for (int i = 0; i < getCategory().size(); i++) {
      uCategory.getItems().addAll(getCategory().get(i).getCategoryName());
    }
    for (int i = 0; i < getTax().size(); i++) {
      uTax.getItems().addAll(getTax().get(i).getTaxPercent());
    }
    uId.setText(String.valueOf(productSelected.getId()));
    uNameProduct.setText(productSelected.getProductName());
    uBarcode.setText(String.valueOf(productSelected.getBarcode()));
    uStock.setText(String.valueOf(productSelected.getStock()));
    uPrice.setText(String.valueOf(productSelected.getPrice()));
    uCategory.setValue(productSelected.getCategory());
    uBrand.setValue(productSelected.getBrand());
    uTax.setValue(productSelected.getTax());
  }

  //----------------------------- Função de Iniciar o Controlador -----------------------------------------------
  /*
   * faz load da lista dos produtos assim que o Product.fxml faz load
   * o @FXML e para indicar ao FXML para olhar isto quando faz load ao ficheiro
   */
  @FXML
  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    /*EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle (ActionEvent e) {
        try {
          System.out.println(e);
          load.loadUI("AddProduct", e);
        } catch (SQLException ex) {
          ex.printStackTrace();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    };
    btAddProduct.setOnAction(event);*/
    String txt = url.toString();
    if (txt.contains("/Add")) {
      System.out.println("/add");
      onSelectAddProduct();
    } else if (txt.contains("/Product")) {
      System.out.println("/prod");
      onListProduct();
    }
  }

  /*
   * Chamada ao iniciar na tab de dar adicionar um produto
   */
  @FXML
  public void onSelectAddProduct () {
    for (int i = 0; i < getBrand().size(); i++) {
      cBrand.getItems().addAll(getBrand().get(i).getBrandName());
    }
    for (int i = 0; i < getCategory().size(); i++) {
      cCategory.getItems().addAll(getCategory().get(i).getCategoryName());
    }
    for (int i = 0; i < getTax().size(); i++) {
      cTax.getItems().addAll(getTax().get(i).getTaxPercent());
    }
  }

  //------------------------------------------- Listar Productos -----------------------------------------------
  /*
   * Função chamada ao selecionar a tab Produtos
   * Chama que função de listar os produto
   */
  @FXML
  public void onListProduct () {
    colId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
    colBarcode.setCellValueFactory(new PropertyValueFactory<Product, Integer>("barcode"));
    colProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
    colCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
    colBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
    colStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
    colPrice.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
    colTax.setCellValueFactory(new PropertyValueFactory<Product, Integer>("tax"));

    tvListProducts.setItems(model.addListOfProducts());
  }
}
