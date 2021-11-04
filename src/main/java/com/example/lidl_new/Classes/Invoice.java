package com.example.lidl_new.Classes;

public class Invoice {


  public String nameProduct;
  public int quantity, idClient;
  public double price;


  public Invoice (String product, int idClient, int quantity, double price) {
    this.nameProduct = product;
    this.idClient = idClient;
    this.quantity = quantity;
    this.price = price;
  }

  public Invoice () {

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

  public void setIdClient (int idClient) {
    this.idClient = idClient;
  }

  public double getPrice () {
    return price;
  }

  public void setPrice (double price) {
    this.price = price;
  }
}
