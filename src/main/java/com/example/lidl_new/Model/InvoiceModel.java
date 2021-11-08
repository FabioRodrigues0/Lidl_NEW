package com.example.lidl_new.Model;

import com.example.lidl_new.Classes.Invoice;
import com.example.lidl_new.Database.Conn;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceModel {
  public ObservableList<Invoice> invoiceList;
  int id;

  public InvoiceModel (int i) {
    id = i;
    invoiceList = FXCollections.observableArrayList();
  }

  public ObservableList<Invoice> getInvoiceModel (int client) {
    cleanInvoiceList();
    id = client;
    try {
      Statement stmt = Conn.createStatement();
      ResultSet rs = stmt.executeQuery("CALL lidl_java.listinvoices(" + id + ")");
      while (rs.next()) {
        invoiceList.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getString(4)));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return invoiceList;
  }

  public void cleanInvoiceList () {
    invoiceList.clear();
  }

  public ObservableList<Invoice> getInvoices () {
    return invoiceList;
  }

  public void setInvoices (ObservableList<Invoice> invoiceList) {
    this.invoiceList = invoiceList;
  }


}
