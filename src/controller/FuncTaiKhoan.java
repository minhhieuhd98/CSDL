/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database_conf.connectDB;
import frm.Taikhoan;
import frm.Trangchu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mhieu
 */
public class FuncTaiKhoan extends Taikhoan{
    private connectDB db = new connectDB();
    private Connection conn = db.connect();
   
    private PreparedStatement stmt;
    
    public void showTaiKhoan(JTable jT){
        String query = "SELECT nv.manv, nv.tennv, tk.tendn, tk.matkhau, tk.quyen FROM nhanvien nv "
                + "INNER JOIN taikhoan tk ON nv.manv = tk.manv";
        
        int c = 0;
        
        Object[] obj = 
                new Object[]{"STT", "manv", "tennv", "tendn", "matkhau", "quyen"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        jT.setModel(tableModel);
        
        ResultSet rs = db.ExcuteGetResultData(query);
        
        try {
            while(rs.next()){
                Object[] item = new Object[6];
                item[0] = c++;
                item[1] = rs.getInt("manv");
                item[2] = rs.getString("tennv");
                item[3] = rs.getString("tendn");
                item[4] = rs.getString("matkhau");
                item[5] = rs.getBoolean("quyen");
                
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isMaNV(int x){
        String truyvan ="select * from taikhoan tk where tk.manv=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(truyvan);
            stmt.setInt(1, x);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return true;
  
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean isTenDN(String tendn){
        String truyvan ="select * from taikhoan tk where tk.tendn=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(truyvan);
            stmt.setString(1, tendn);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return true;
  
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
    }
    
    public boolean isPassWord(String pass){
        String truyvan ="select * from taikhoan tk where tk.matkhau=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(truyvan);
            stmt.setString(1, pass);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return true;
  
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
    }
    
    public boolean themTK(Taikhoan tk){
        String query = "INSERT INTO taikhoan(manv, tendn, matkhau, quyen) values (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, tk.getMaNV());
            stmt.setString(2, tk.getTenDN());
            stmt.setString(3, tk.getMatKhau());
            stmt.setBoolean(4, tk.getQuyen());
            if(stmt.executeUpdate() > 0) return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return false;
    }
    public boolean xoatk(int manv){
        String query = "DELETE FROM taikhoan tk WHERE tk.manv = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, manv);
            if(stmt.executeUpdate()>0) return true;
            else return false;
        } catch (SQLException ex) {
            Logger.getLogger(FuncTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
