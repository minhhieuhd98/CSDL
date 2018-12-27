/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database_conf.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mhieu
 */
public class FuncHoaDon {
    private connectDB db = new connectDB();
    private Connection conn = db.connect();
   
    private PreparedStatement stmt;
    public FuncHoaDon(){
        
    }
    public ResultSet findID(int mahd){
        ResultSet rs = null;
        String sql = "SELECT hd.mahd, kh.tenkh, nv.tennv, hd.ngaylap, hd.tongtien FROM hoadon hd INNER JOIN khachhang kh ON kh.makh = hd.makh" +
                                                                                                " INNER JOIN nhanvien nv ON nv.manv = hd.manv" +
                    " WHERE hd.mahd = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mahd);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }   
    
    public ResultSet thongkeSeri(int mahd){
        ResultSet rs = null;
        String sql = "SELECT chd.masp, count(xs.maseri) as slg FROM  chitiethd chd INNER JOIN xuatserial xs ON xs.mahd = chd.mahd" +
                            " WHERE chd.mahd = ?" +
                            " GROUP BY chd.masp";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mahd);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet findKH(int makh){
        ResultSet rs = null;
        String sql = "SELECT * FROM hoadon hd INNER JOIN chitiethd chd ON hd.mahd = chd.mahd WHERE hd.makh = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, makh);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }   
    
    public ResultSet findNV(int manv){
        ResultSet rs = null;
        String sql = "SELECT * FROM hoadon hd INNER JOIN chitiethd chd ON hd.mahd = chd.mahd WHERE hd.manv = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, manv);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }   
}
