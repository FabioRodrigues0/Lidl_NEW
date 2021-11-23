/*
 * Project: Lidl
 * User : fabiorodrigues.
 * Date: 10/25/21, 12:11 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */

package com.example.lidl_new.Model;

import com.example.lidl_new.Classes.Product;
import com.example.lidl_new.Database.Conn;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductModel {

  public ObservableList<Product> productList;

  public ProductModel () {
    productList = FXCollections.observableArrayList();

  }


  public static void createProduct (int barcode, String productName, int category, int brand, int stock, double price, int tax) {

    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("INSERT INTO product(barcode, stock, category, brand, tax, product, price) " + "VALUES ('" + barcode + "', '" + stock + "', '" + category + "', '" + brand + "', '" + tax + "', '" + productName + "', '" + price + "')");
      System.out.println("Produto inserido com sucesso!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void updateProduct (int idProduto, int barcode, String productName, int category, int brand, int stock, float price, int tax) {

    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("UPDATE `product` SET `barcode`= '" + barcode + "', `stock`= '" + stock + "', `category`= '" + category + "', " + "`brand`= '" + brand + "', `tax`= '" + tax + "', `product`= '" + productName + "', `price`= '" + price + "' WHERE `id`= '" + idProduto + "'");
      System.out.println("Produto Atualizado com sucesso!");
    } catch (Exception e) {
      System.out.println(e);

    }
    addListOfProducts();
  }

  public ObservableList<Product> addListOfProducts () {
    cleanproductList();
    try {
      Statement stmt = Conn.createStatement();
      ResultSet rs = stmt.executeQuery("CALL lidl_java.listProducts()");
      while (rs.next()) {
        productList.add(new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8)));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return productList;
  }

  public void cleanproductList () {
    productList.clear();
  }

  public void deleteProduct (int idProduto) {
    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("DELETE `product` WHERE `id`=" + idProduto + "");
      System.out.println("Produto Apagado com sucesso!");
    } catch (Exception e) {
      System.out.println(e);

    }
    addListOfProducts();
  }

  public ObservableList<Product> getProducts () {
    return productList;
  }

  public void setProducts (ObservableList<Product> productsList) {
    this.productList = productsList;
  }

}
