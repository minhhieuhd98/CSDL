/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template 
file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mhieu
 */
public class connectDB {
    private Connection con; 
    public Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/qlkh";
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, "postgres", "123456");
            if(con != null) System.out.println("Thanh cong");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("That bai");
        }
        return con;
    }
    
    // GET DATA FROM DATABASE
    public ResultSet ExcuteGetResultData(String query) {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    public void ExcuteUpdateData(String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
