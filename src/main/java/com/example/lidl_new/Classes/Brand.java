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
 * Time: 11:26
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
package com.example.lidl_new.Classes;

import com.example.lidl_new.Database.Conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class Brand {
    public int id;
    public String brandName;

    public Brand (int id, String brandName){
        this.id = id;
        this.brandName = brandName;
    }

    public int getId () {
        return id;
    }
    public String getBrandName(){return brandName;}

    public static LinkedList<Brand> getBrand() {
        LinkedList<Brand> brandList = new LinkedList<>();
        Brand r;
        try {
            Statement stmt = Conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product_brand ORDER BY brand ASC");
            while (rs.next()){
                r = new Brand(rs.getInt(1), ""+rs.getString(2));
                brandList.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return brandList;
    }
}
