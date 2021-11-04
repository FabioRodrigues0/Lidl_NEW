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

package com.example.lidl_new.Classes;

import com.example.lidl_new.Database.Conn;

/**
 * Project: Lidl
 * User: pedrosilva
 * Date: 13/10/2021
 * Time: 12:32
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
public class Client {


    public int id;
    public String name;
    public String ccNumber;
    public int nif;
    public int phoneNumber;
    public String email;
    public String password;

    public Client (int id, String name, String ccNumber, int nif, int phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.ccNumber = ccNumber;
        this.nif = nif;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    public int getId () {return id;}
    public String getName () {
        return name;
    }
    public String getCcNumber () {
        return ccNumber;
    }
    public int getNif () {
        return nif;
    }
    public int getPhoneNumber () {
        return phoneNumber;
    }
    public String getEmail () {
        return email;
    }
    public String getPassword () {
        return password;
    }
    public int setId (int id) {
        return id;
    }

    public String setName (String name) {
        return name;
    }

    public String setCcNumber (String ccNumber) {
        return ccNumber;
    }

    public int setNif (int nif) {
        return nif;
    }

    public int setPhoneNumber (int phoneNumber) {
        return phoneNumber;
    }

    public String setEmail (String email) {
        return email;
    }

    public String setPassword (String password) {
        return password;
    }
}
