package com.example.lidl_new.Controller;

import com.example.lidl_new.MainApp;
import com.example.lidl_new.Model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.lidl_new.Classes.Brand.getBrand;
import static com.example.lidl_new.Classes.Category.getCategory;
import static com.example.lidl_new.Classes.Tax.getTax;

public class AddProduct implements Initializable {
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
  public Button btUClose;


  public void display () throws IOException {
    Stage window = new Stage();
    window.initModality(Modality.WINDOW_MODAL);
    window.setTitle("Criar Produto");
    window.setMinWidth(250);

    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("AddProduct.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
    window.setScene(scene);
    window.show();
  }

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
    float price = Float.parseFloat(cPrice.getText());
    int category = 99, brand = 99, tax = 99;
    for (int i = 0; i < getCategory().size(); i++) {
      if (Objects.equals(getCategory().get(i).getCategoryName(), cCategory.getValue())) {
        category = getCategory().get(i).getId();
      }
    }
    for (int i = 0; i < getBrand().size(); i++) {
      if (Objects.equals(getBrand().get(i).getBrandName(), cBrand.getValue())) {
        brand = getBrand().get(i).getId();
      }
    }
    for (int j = 0; j < getTax().size(); j++) {
      if (Objects.equals(getTax().get(j).getTaxPercent(), cTax.getValue())) {
        tax = getTax().get(j).getId();
      }
    }

    ProductModel.createProduct(barcode, cNameProduct.getText(), category, brand, stock, price, tax);
  }

  @FXML
  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    onSelectAddProduct();
  }

  //------------------------------------------- Criar Producto -----------------------------------------------
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

  @FXML
  public void clearText () {
    cNameProduct.clear();
    cBarcode.clear();
    cStock.clear();
    cPrice.clear();
  }
}
