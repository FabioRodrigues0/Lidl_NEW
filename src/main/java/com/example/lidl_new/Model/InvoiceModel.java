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
import com.example.lidl_new.Controller.HomeController;
import com.example.lidl_new.Database.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

public class InvoiceModel {
  public ObservableList<Invoice> invoiceList;

  public InvoiceModel (String nameProduct, int idClient, int quantity, double price) {
    invoiceList = FXCollections.observableArrayList();
    invoiceList.add(new Invoice(nameProduct, idClient, quantity, price));

  }

  public InvoiceModel () {

  }

  /**
   * SELECT Orders.OrderID, Customers.CustomerName
   * FROM Orders
   * INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID;
   */
    /*public static void ReadInvoice(){
        ReadClient();
        try {
            Statement stmt = Conn.createStatement();
            ResultSet rs = stmt.executeQuery("select invoice_id, product_id, quantity, product.product, product.price, invoice.client, invoice.total_price, client.name, client.nif, client.card,
client_card.points, client_card.total_purchases, client_card.barcode
from ((((invoice_product
inner join invoice on invoice_id = invoice.id)
inner join product on product_id = product.id)
inner join client on client = client.id)
inner join client_card on card = client_card.id)
where client.id = 28");Substituir o 28 pelo id que vem do FX
            System.out.println("Lista de Clientes: ");
            while (rs.next()){
                System.out.println(rs.getInt(1) + " - Nome: "
                        + rs.getString(2) + " - NIF: "
                        + rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

  //------------------------------------- CREATE INVOICE_PRODUCT / INVOICE ----------------------------------------//
  public static void CreateInvoiceProduct () throws SQLException {
    // Lista clientes
    //ReadClient();
    // Guarda o ID do cliente escolhido
    int idClient = 1;// !todo Substituir o 1 pelo id que vem do FX
    // Inicializa a variável referente ao preço total da compra
    double total_price = 0;
    int invoiceId = 0;
    String buy;
    double totalPurshase = 0;

    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("INSERT INTO invoice(client, total_price) VALUES ('" + idClient + "', '" + total_price + "');");
      ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() as invoiceLastId FROM invoice");
      while (rs.next())
        invoiceId = rs.getInt("invoiceLastId");
    } catch (Exception e) {
      System.out.println(e);
    }

    Scanner in = new Scanner(System.in);
    do {
      //Product.ReadProducts();
      System.out.println("Escolha um produto: ");
      int product_id = in.nextInt();
      System.out.println("Quantidade: ");
      int quantity = in.nextInt();
      try {
        Statement stmt = Conn.createStatement();
        stmt.executeUpdate("INSERT INTO invoice_product(invoice_id, product_id, quantity) " + "VALUES ('" + invoiceId + "', '" + product_id + "', '" + quantity + "')");
        myResult result = ReadInfoProduct(product_id);
        double newStock = result.getFirst();
        newStock -= quantity;
        UpdateStock(newStock, product_id);
        double productPrice = result.getSecond();
        // Atualiza valor total da compra na tabela invoice
        double purshase = (double) (productPrice * quantity);
        // Casting
        totalPurshase += (double) (purshase);
      } catch (Exception e) {
        System.out.println(e);
      }

      System.out.println("Continuar a comprar? (Y/N)");
      buy = in.next();
    } while (Objects.equals(buy, "Y") || Objects.equals(buy, "y"));
    UpdatePrice(invoiceId, totalPurshase);
    CardModel.UpdatePointsCard(idClient, totalPurshase);
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

  public ObservableList<Invoice> getInvoices () {
    return invoiceList;
  }

  public void setInvoices (ObservableList<Invoice> invoiceList) {
    this.invoiceList = invoiceList;
  }

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
