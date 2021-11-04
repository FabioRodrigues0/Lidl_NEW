/*
 * Project: Lidl
 * User : fabiorodrigues.
 * Date: 10/25/21, 2:47 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */
/*
 * Project: Lidl
 * User: pedrosilva
 * Date: 20/10/2021
 * Time: 11:38
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */

package com.example.lidl_new.Classes;

import com.example.lidl_new.Database.Conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class Tax {
    public int id;
    public int taxPercent;

    public Tax (int id, int taxPercent) {
        this.id = id;
        this.taxPercent = taxPercent;
    }

    public int getId () {
        return id;
    }
    public int getTaxPercent () {
        return taxPercent;
    }

    public static LinkedList<Tax> getTax () {
        LinkedList<Tax> taxList = new LinkedList<>();
        Tax t;
        try {
            Statement stmt = Conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product_tax ORDER BY tax ASC");
            while (rs.next()) {
                t = new Tax(rs.getInt(1), rs.getInt(2));
                taxList.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return taxList;
    }
}
