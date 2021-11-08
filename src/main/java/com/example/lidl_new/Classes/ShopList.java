package com.example.lidl_new.Classes;

public class ShopList {


  public String nameProduct;
  public int quantity;
  public int idProduct;
  public float price;

  public ShopList (int idProduct, String product, int quantity, float price) {
    this.idProduct = idProduct;
    this.nameProduct = product;
    this.quantity = quantity;
    this.price = price;
  }


  public ShopList () {

  }

  public int getIdProduct () {
    return idProduct;
  }

  public void setIdProduct (int idProduct) {
    this.idProduct = idProduct;
  }

  public String getNameProduct () {
    return nameProduct;
  }

  public void setNameProduct (String nameProduct) {
    this.nameProduct = nameProduct;
  }

  public int getQuantity () {
    return quantity;
  }

  public void setQuantity (int quantity) {
    this.quantity = quantity;
  }

  public float getPrice () {
    return price;
  }

  public void setPrice (float price) {
    this.price = price;
  }
}
