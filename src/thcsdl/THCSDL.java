/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thcsdl;
import database_conf.connectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class THCSDL {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        String query = "SELECT sp.masp,sp.tensp FROM sanpham sp";
        connectDB Test = new connectDB();
        ResultSet rs = Test.ExcuteGetResultData(query);
        
        try {
            while(rs.next()){
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                System.out.println(masp+" "+tensp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(THCSDL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
