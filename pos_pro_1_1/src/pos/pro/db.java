/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DILSHANI
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    public static Connection mycon() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/oop_db"; // Replace with your MySQL database URL
            String user = "root"; // Replace with your MySQL username
            String password = ""; // Replace with your MySQL password

            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or show an error message
        }
        return con;
    }
}
