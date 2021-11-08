package com.example.lidl_new.Classes;

public class InvoiceProduct {
  public int id;
  public int invoice_id;
  public int product_id;
  public float quantity;
  public String productName;
  public float productPrice;

  public InvoiceProduct (int id, int invoice_id, int product_id, float quantity, String productName, float productPrice) {
    this.id = id;
    this.invoice_id = invoice_id;
    this.product_id = product_id;
    this.quantity = quantity;
    this.productName = productName;
    this.productPrice = productPrice;
  }

  public int getId () {
    return id;
  }

  public int getInvoice_id () {
    return invoice_id;
  }

  public int getProduct_id () {
    return product_id;
  }

  public String getProductName () {
    return productName;
  }

  public float getProductPrice () {
    return productPrice;
  }

  public float getQuantity () {
    return quantity;
  }

}
