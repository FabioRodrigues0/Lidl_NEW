package com.example.lidl_new.Model;

import com.example.lidl_new.Classes.Invoice;
import com.example.lidl_new.Classes.InvoiceProduct;
import com.example.lidl_new.Database.Conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceProductModel {
  public ObservableList<InvoiceProduct> invoiceProductList;

  public InvoiceProductModel () {
    invoiceProductList = FXCollections.observableArrayList();
  }

  public ObservableList<InvoiceProduct> ListOfProductsInvoices (int id) {
    cleanInvoiceList();
    try {
      Statement stmt = Conn.createStatement();
      ResultSet rs = stmt.executeQuery("CALL lidl_java.listinvoiceproduct(" + id + ")");
      if (rs.isAfterLast()) {
        invoiceProductList.add(new InvoiceProduct(rs.getInt(1), rs.getInt(2), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getFloat(5)));

      } else {
        while (rs.next()) {
          invoiceProductList.add(new InvoiceProduct(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getFloat(6)));
        }
      }
    } catch (NumberFormatException | SQLException e) {
      System.out.println(e);
    }
    return invoiceProductList;
  }

  public ObservableList<InvoiceProduct> cleanInvoiceList () {
    for (int i = 0; i < invoiceProductList.size(); i++) {
      invoiceProductList.remove(i);
    }
    return invoiceProductList;
  }

  public ObservableList<InvoiceProduct> getProductsInvoices () {
    return invoiceProductList;
  }

  public void setProductsInvoices (ObservableList<InvoiceProduct> invoiceProductList) {
    this.invoiceProductList = invoiceProductList;
  }
}
