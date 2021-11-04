/*
 * Project: Lidl
 * User : fabiorodrigues.
 * Date: 10/25/21, 2:47 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */

/*
 * Project: Lidl_4
 * User : fabiorodrigues.
 * Date: 10/22/21, 12:55 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */

/*
 * Project: Lidl_4
 * User : fabiorodrigues.
 * Date: 10/20/21, 2:27 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */

package com.example.lidl_new.Model;

import com.example.lidl_new.Classes.Brand;
import com.example.lidl_new.Classes.Client;
import com.example.lidl_new.Database.Conn;
import java.sql.Statement;
import java.util.Date;
/**
 * Project: Lidl
 * User: pedrosilva
 * Date: 13/10/2021
 * Time: 12:32
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
public class ClientModel {
    Client c;
    public ClientModel (int id, String name, String ccNumber, int nif, int phoneNumber, String email, String password) {
        c = new Client(id, name, ccNumber, nif, phoneNumber, email, password);
    }

    public static void CreateClient (String name, String ccNumber, int nif, int phone_number,
                                     String address, int number, String floor, String location,
                                     String email, String password) {
        try {
            Statement stmt = Conn.createStatement();
            stmt.executeQuery("CALL lidl_java.registerclient('"+ name +"', '"+ ccNumber +"', '"+ nif +"', '"+ phone_number +"', '"+ address +"', '"+number+"', '"+ floor +"', '"+ location +"', '"+ email+"', '"+ password +"')");
            System.out.println("Cliente inserido com sucesso!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
