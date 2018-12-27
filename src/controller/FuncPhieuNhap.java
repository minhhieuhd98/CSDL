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
public class FuncPhieuNhap {
    private connectDB db = new connectDB();
    private Connection conn = db.connect();
   
    private PreparedStatement stmt;
    public FuncPhieuNhap(){
        
    }
    public ResultSet findID(int manpp){
        ResultSet rs = null;
        String sql = "SELECT pn.mapn, npp.ten_nhapp, nv.tennv, pn.ngaylap, pn.tongtien FROM phieunhap pn INNER JOIN nhaphanphoi npp ON npp.id = pn.ma_nhapp" +
                                                                                                " INNER JOIN nhanvien nv ON nv.manv = pn.manv " +
                    "WHERE pn.mapn = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, manpp);
            
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }   
    
    public ResultSet thongkeSeri(int mahd){
        ResultSet rs = null;
        String sql = "SELECT cpn.masp, count(ns.maseri) as slg FROM  chitietpn cpn INNER JOIN nhapserial ns ON ns.mapn = cpn.ma_pn" +
                        " WHERE cpn.ma_pn = ?" +
                        " GROUP BY cpn.masp";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mahd);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
