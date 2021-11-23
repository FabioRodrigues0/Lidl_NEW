package com.example.lidl_new.Controller;

import com.example.lidl_new.Classes.Product;
import com.example.lidl_new.Classes.ProductHolder;
import com.example.lidl_new.Database.Conn;
import com.example.lidl_new.MainApp;
import com.example.lidl_new.Model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProductController implements Initializable {
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
  @FXML
  public HBox tabUpdateProduct;
  @FXML
  public HBox tabAddProduct;
  @FXML
  public HBox tabProduct;


  ProductModel model = new ProductModel();
  AddProduct add = new AddProduct();
  UpdateProduct update = new UpdateProduct();
  DeleteWindow delete = new DeleteWindow();
  MainAppController main = new MainAppController();

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
   * Chamada ao iniciar na tab de dar adicionar um produto
   */
  @FXML
  public void onAddProduct () throws IOException {
    add.display();
  }

  //------------------------------------------- Update Product -----------------------------------------------
  /*
   * e chama a função que vai escrever os items do produto nas caixas de texto
   *  através da lista de produto selecionado
   */
  @FXML
  public void onUpdateProduct (MouseEvent event) throws IOException {
    Product productSelected = tvListProducts.getSelectionModel().getSelectedItem();
    try {
      ProductHolder holder = ProductHolder.getInstance();
      holder.setUser(productSelected);
      update.display();
    } catch (IOException e) {
      System.err.println(String.format("Error: %s", e.getMessage()));
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
    onListProduct();
  }


  //------------------------------------------- Listar Products -----------------------------------------------
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

  //------------------------------------------- Delete Products -----------------------------------------------
  /*
   * Chama a função de criar uma nova janela,
   * para comfirmação se deseja Apagar o produto
   */
  @FXML
  public void onDeleteProduct () {
    boolean result = false;
    Product productSelected = tvListProducts.getSelectionModel().getSelectedItem();
    try {
      result = delete.display();
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (result) {
      int idProduct = productSelected.getId();
      model.deleteProduct(idProduct);
    }
  }
}
