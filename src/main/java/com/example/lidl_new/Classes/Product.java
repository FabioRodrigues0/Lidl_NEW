/*
 * Project: Lidl
 * User : fabiorodrigues.
 * Date: 10/25/21, 2:20 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */
/*
 * Project: Lidl
 * User: pedrosilva
 * Date: 19/10/2021
 * Time: 20:04
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
package com.example.lidl_new.Classes;

public class Product {

  public int id, barcode, stock, tax;
  public String productName, brand, category;
  public float price;

  public Product (int id, int barcode, String productName, String category, String brand, int stock, float price, int tax) {
    this.id = id;
    this.barcode = barcode;
    this.productName = productName;
    this.category = category;
    this.brand = brand;
    this.stock = stock;
    this.price = price;
    this.tax = tax;
  }

  public int getId () {
    return id;
  }

  public int getBarcode () {
    return barcode;
  }

  public String getProductName () {
    return productName;
  }

  public int getStock () {
    return stock;
  }

  public String getCategory () {
    return category;
  }

  public String getBrand () {
    return brand;
  }

  public int getTax () {
    return tax;
  }

  public float getPrice () {
    return price;
  }


}
