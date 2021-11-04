module com.example.lidl_new {
  requires javafx.controls;
  requires javafx.graphics;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.core;
  requires java.logging;
  requires java.sql;
  requires mysql.connector.java;


  opens com.example.lidl_new to javafx.fxml;
  exports com.example.lidl_new;
  exports com.example.lidl_new.Controller;
  opens com.example.lidl_new.Controller to javafx.fxml;
  exports com.example.lidl_new.Model;
  opens com.example.lidl_new.Model to javafx.fxml;
  exports com.example.lidl_new.Classes;
  opens com.example.lidl_new.Classes to javafx.fxml;
}