package com.example.lidl_new.Controller;

import com.example.lidl_new.Classes.*;
import com.example.lidl_new.MainApp;
import com.example.lidl_new.Model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.lidl_new.Classes.Brand.getBrand;
import static com.example.lidl_new.Classes.Category.getCategory;
import static com.example.lidl_new.Classes.Tax.getTax;

public class UpdateProduct implements Initializable {
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
  @FXML
  public HBox tabUpdateProduct;
  ProductModel model = new ProductModel();

  //------------------------------------------- JANELA -----------------------------------------------
  /*
   * Cria uma nova janela para a scene UpdateProduct
   */
  @FXML
  public void display () throws IOException {
    Stage window = new Stage();

    window.initModality(Modality.WINDOW_MODAL);
    window.setTitle("Update");
    window.setMinWidth(250);

    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Views/UpdateProduct.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
    window.setScene(scene);
    window.show();
  }

  //------------------------------------------- Update Produto -----------------------------------------------
  /*
   * Função chamada no clicar do botão de Update
   * Chama que função de dar update ao produto
   */
  @FXML
  public void setUpdateProduct () {
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

    model.updateProduct(id, barcode, uNameProduct.getText(), category, brand, stock, price, tax);
  }

  //------------------------------------------- initialize -----------------------------------------------
  /*
   * Arranca assim que o fxml e feito load
   */
  @FXML
  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    update();
    receiveData();
  }

  /*
   * Cria as opções para o choicebox
   */
  @FXML
  public void update () {
    for (Brand b : getBrand()) {
      uBrand.getItems().addAll(b.getBrandName());
    }
    for (int i = 0; i < getBrand().size(); i++) {
    }
    for (Category c : getCategory()) {
      uCategory.getItems().addAll(c.getCategoryName());
    }
    for (Tax t : getTax()) {
      uTax.getItems().addAll(t.getTaxPercent());
    }
  }

  @FXML
  void receiveData () {
    ProductHolder holder = ProductHolder.getInstance();
    Product p = holder.getUser();
    String id = String.valueOf(p.getId());
    String productName = p.getProductName();
    String barcode = String.valueOf(p.getBarcode());
    String stock = String.valueOf(p.getStock());
    String category = String.valueOf(p.getCategory());
    String brand = String.valueOf(p.getBrand());
    String price = String.valueOf(p.getPrice());
    int tax = p.getTax();
    updateProduct(id, productName, barcode, stock, price, category, brand, tax);
  }

  /*
   * Escreve o producto nas caixas de texto e seleciona os choisebox
   * para se mudar e atualizar o producto
   */
  @FXML
  public void updateProduct (String a, String b, String c, String d, String e, String f, String g, int h) {
    uId.setText(a);
    uNameProduct.setText(b);
    uBarcode.setText(c);
    uStock.setText(d);
    uPrice.setText(e);
    uCategory.setValue(f);
    uBrand.setValue(g);
    uTax.setValue(h);
  }

}