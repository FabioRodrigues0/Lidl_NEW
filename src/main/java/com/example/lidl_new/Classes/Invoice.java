package com.example.lidl_new.Classes;

public class Invoice {
  public int id;
  public int idClient;
  public float total_price;
  public String date;

  public Invoice (int id, int idClient, float total_price, String date) {
    this.id = id;
    this.idClient = idClient;
    this.total_price = total_price;
    this.date = date;
  }

  public int getId () {
    return id;
  }

  public int getIdClient () {
    return idClient;
  }

  public float getTotal_price () {
    return total_price;
  }

  public String getDate () {
    return date;
  }
}
