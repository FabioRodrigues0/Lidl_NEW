package com.example.lidl_new.Controller;

import com.example.lidl_new.Classes.Brand;
import com.example.lidl_new.Classes.Category;
import com.example.lidl_new.Classes.Tax;
import com.example.lidl_new.MainApp;
import com.example.lidl_new.Model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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

    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("UpdateProduct.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
    window.setScene(scene);
    window.show();
    System.out.println("Entrei3");
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

  /*
   * Escreve o producto nas caixas de texto e seleciona os choisebox
   * para se mudar e atualizar o producto
   */
  @FXML
  public void updateProduct (String a, String b, String c, String d, String e) {
    System.out.println("Entrei1");
    uId.setText("6");
    uNameProduct.setText("7");
    uBarcode.setText("8");
    uStock.setText("9");
    uPrice.setText("10");
/*    uId.setText(a);
    uNameProduct.setText(b);
    uBarcode.setText(c);
    uStock.setText(d);
    uPrice.setText(e);
    uCategory.setValue("");
    uBrand.setValue("");
    uTax.setValue(1);*/
  }

  //------------------------------------------- initialize -----------------------------------------------
  /*
   * Arranca assim que o fxml e feito load
   */
  @FXML
  @Override
  public void initialize (URL url, ResourceBundle resourceBundle) {
    System.out.println("Entrei5");
    update();
  }

  /*
   * Cria as opções para o choicebox
   */
  @FXML
  public void update () {
    System.out.println("Entrei4");
    uId.setText("1");
    uNameProduct.setText("2");
    uBarcode.setText("3");
    uStock.setText("4");
    uPrice.setText("5");
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
}