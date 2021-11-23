package com.example.lidl_new.Classes;

public final class ProductHolder {

  private final static ProductHolder INSTANCE = new ProductHolder();
  private Product product;

  private ProductHolder () {
  }

  /**
   * Cria uma instancia para guardar o produto na transição de scenes
   *
   * @return INSTANCE
   */
  public static ProductHolder getInstance () {
    return INSTANCE;
  }

  /**
   * @return product
   */
  public Product getUser () {
    return this.product;
  }

  public void setUser (Product p) {
    this.product = p;
  }
}