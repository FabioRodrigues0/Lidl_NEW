/**
 * Project: Lidl
 * User: pedrosilva
 * Date: 20/10/2021
 * Time: 14:31
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
package com.example.lidl_new.Model;

import com.example.lidl_new.Classes.Invoice;
import com.example.lidl_new.Database.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceModel {
  public ObservableList<Invoice> invoiceList;

  public InvoiceModel () {
    invoiceList = FXCollections.observableArrayList();

  }

  //------------------------------------- CREATE INVOICE_PRODUCT / INVOICE ----------------------------------------//
  public void CreateInvoiceProduct (int Client, float tp) throws SQLException {
    // Lista clientes
    //ReadClient();
    // Guarda o ID do cliente escolhido
    int idClient = Client;
    // Inicializa a variável referente ao preço total da compra
    float total_price = tp;
    int invoiceId = 0;
    float totalPurshase = 0;

    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("INSERT INTO invoice(client, total_price) VALUES ('" + idClient + "', '" + total_price + "');");
      ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() as invoiceLastId FROM invoice");
      while (rs.next())
        invoiceId = rs.getInt("invoiceLastId");
      System.out.println(invoiceId + " este id");
    } catch (Exception e) {
      System.out.println(e);
    }
    for (Invoice r : invoiceList) {
      try {
        Statement stmt = Conn.createStatement();
        System.out.println(invoiceId + " aqui este id");
        stmt.executeUpdate("INSERT INTO invoice_product(invoice_id, product_id, quantity) " + "" + "VALUES ('" + invoiceId + "', '" + r.getIdProduct() + "', '" + r.getQuantity() + "')");
        myResult result = ReadInfoProduct(r.getIdProduct());
        double newStock = result.getFirst();
        newStock -= r.getQuantity();
        UpdateStock(newStock, r.getIdProduct());
        double productPrice = result.getSecond();
        // Atualiza valor total da compra na tabela invoice
        double purshase = (double) (productPrice * r.getQuantity());
        // Casting
        totalPurshase += (double) (purshase);
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    UpdatePrice(invoiceId, totalPurshase);
    CardModel.UpdatePointsCard(idClient, totalPurshase);
  }

  //------------------------------------- VERIFICAR PRICE / STOCK ----------------------------------------//
  public static myResult ReadInfoProduct (int product_id) {
    double result = 0;
    double price = 0;
    try {
      Statement stmt2 = Conn.createStatement();
      ResultSet rs = stmt2.executeQuery("SELECT stock, price FROM product where id = '" + product_id + "'");
      while (rs.next()) {
        result = rs.getDouble(1);
        price = rs.getDouble(2);
      }
      System.out.println("Leitura de stock original feito com sucesso! Result --> " + result);
    } catch (Exception e) {
      System.out.println(e);
    }
    return new myResult(result, price);
  }

  //------------------------------------- UPDATE PRODUCT STOCK ---------------------------------------------//
  static double UpdateStock (double stock, int id) {
    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("UPDATE product SET stock = '" + stock + "' WHERE id = '" + id + "'");
      System.out.println("Stock atualizado com sucesso! Stock atual --> " + stock);
    } catch (Exception e) {
      System.out.println(e);
    }
    return stock;
  }

  //------------------------------------- UPDATE INVOICE_PRODUCT / INVOICE ----------------------------------------//
  static double UpdatePrice (int id, double total_price) {
    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("UPDATE invoice SET total_price = '" + total_price + "' WHERE id = '" + id + "'");
      System.out.println("Update do preço total (compra) na tabela invoice feito com sucesso!");
    } catch (Exception e) {
      System.out.println(e);
    }
    return total_price;
  }

  //----------------------- UPDATE A LISTA DE PRODUTOS QUE CLIENTE QUER COMPRAR ----------------------------------//
  public ObservableList<Invoice> addRowInvoice (int productIndex, String nameProduct, int quantity, float price) {
    invoiceList.add(new Invoice(productIndex, nameProduct, quantity, price));
    return invoiceList;
  }

  public ObservableList<Invoice> deleteRowInvoice (Invoice r) {
    invoiceList.remove(r);
    return invoiceList;
  }

  //-------------------------- LISTA DE PRODUTOS QUE CLIENTE QUER COMPRAR ---------------------------------------//
  public ObservableList<Invoice> getInvoices () {
    return invoiceList;
  }

  public void setInvoices (ObservableList<Invoice> invoiceList) {
    this.invoiceList = invoiceList;
  }

  //------------------------------------------------------------------------------------------------------------//
  public static class myResult {
    private final double first;
    private final double second;

    public myResult (double first, double second) {
      this.first = first;
      this.second = second;
    }

    public double getFirst () {
      return first;
    }

    public double getSecond () {
      return second;
    }
  }
}
