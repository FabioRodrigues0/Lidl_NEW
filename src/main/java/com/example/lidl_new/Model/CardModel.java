package com.example.lidl_new.Model;

import com.example.lidl_new.Database.Conn;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Project: Lidl
 * User: pedrosilva
 * Date: 23/10/2021
 * Time: 21:21
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
public class CardModel {


  public static int UpdatePointsCard (int idClient, double totalPurshase) {
    int cardId = 0;
    int pointsAtMoment = 0;

    try {
      Statement stmt2 = Conn.createStatement();
      ResultSet rs = stmt2.executeQuery("SELECT client.id, client.card, client_card.points FROM client " + "INNER JOIN client_card on client.card = client_card.id where client.id = '" + idClient + "'");
      while (rs.next()) {
        cardId = rs.getInt(2);
        pointsAtMoment = rs.getInt(3);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // Update dos pontos do cartão
    // A variável define qual o valor por cada três pontos
    int value = 50;
    int pontosAcomular = 3;

    int x = (int) totalPurshase / value;
    pointsAtMoment += x * pontosAcomular;

    UpdatePoints(cardId, pointsAtMoment);

    return 0;
  }

  static double UpdatePoints (int id, int points) {

    try {
      Statement stmt = Conn.createStatement();
      stmt.executeUpdate("UPDATE client_card SET points = '" + points + "' WHERE id = '" + id + "'");
      System.out.println("Update dos pontos do cartão cliente: " + points);
    } catch (Exception e) {
      System.out.println(e);
    }
    return points;
  }

  public int getPoints (int idClient) {
    int pointsAtMoment = 0;
    try {
      Statement stmt2 = Conn.createStatement();
      ResultSet rs = stmt2.executeQuery("SELECT client.id, client.card, client_card.points FROM client " + "INNER JOIN client_card on client.card = client_card.id where client.id = '" + idClient + "'");
      while (rs.next()) {
        pointsAtMoment = rs.getInt(3);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return pointsAtMoment;
  }
}
