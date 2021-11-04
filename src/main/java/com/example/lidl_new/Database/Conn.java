/*
 * Project: Lidl_4
 * User : fabiorodrigues.
 * Date: 10/20/21, 2:27 PM
 * Author: Fábio Rodrigues
 * Contact: fabio.rod@outlook.com
 */


package com.example.lidl_new.Database;

import java.sql.*;

/**
 * Project: Lidl
 * User: pedrosilva
 * Date: 13/10/2021
 * Time: 11:58
 * Author: Pedro Silva
 * Contact: pedromiguelsilva89@gmail.com
 */
public class Conn {
    //private static Connection connection;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "lidl_java";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + DATABASE;
    private static final String USER = "root";
    private static final String PASS = "";
    public static Connection con;
    public static String query;
    public static Statement stmt;
    public static ResultSet rs;

    public static void getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
            stmt = con.createStatement();
            System.out.println("Ligação feita com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Statement createStatement() throws SQLException{
            con = DriverManager.getConnection(URL, USER, PASS);
            stmt = con.createStatement();
            return stmt;
    }
}


