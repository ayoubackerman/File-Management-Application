/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dahmen
 */
public class DBconnection {

    private static Connection cnx;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/files", "root", "");

        } catch (ClassNotFoundException e) {
            System.err.print("probleme de driver" + e);
        } catch (SQLException e) {
            System.err.print("probleme de connection" + e);
        }
    }

    public static Connection getCnx() {

        return cnx;
    }

}
