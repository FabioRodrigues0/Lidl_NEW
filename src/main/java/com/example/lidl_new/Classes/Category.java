package com.example.lidl_new.Classes;

import com.example.lidl_new.Database.Conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class Category {

  public int id;
  public String categoryName;

  public Category (int id, String categoryName){
    this.id = id;
    this.categoryName = categoryName;
  }

  public int getId () {
    return id;
  }
  public String getCategoryName(){
    return categoryName;
  }

  public static LinkedList<Category> getCategory(){
    LinkedList<Category> categoryList = new LinkedList<>();
    Category c;
    try {
      Statement stmt = Conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM product_category ORDER BY category ASC");
      while (rs.next()){
        c = new Category(rs.getInt(1), ""+rs.getString(2));
        categoryList.add(c);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return categoryList;
  }
}
