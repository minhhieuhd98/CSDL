/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database_conf.connectDB;
import frm.KhachHang;
import frm.Time;
import frm.Trangchu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mhieu
 */
public class FuncKhachHang extends KhachHang{
    private connectDB db = new connectDB();
    private Connection conn = db.connect();
   
    private PreparedStatement stmt;
    
    public boolean xoakhachhang(int makh){
        String query = "DELETE FROM \"khachhang\" as \"kh\" WHERE \"makh\" = ?";           
            
            try {
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, makh);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Đã xóa thành công");
            } catch (SQLException ex) {
                Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
    
    public boolean themkh(KhachHang kh) throws ParseException{
        
        if(kh.getMaKH() > 0) {
            JOptionPane.showMessageDialog(null, "Ban chua nhap ma kh");
            return false;
        } else {
            if(kh.getTenKH().equals("") ||  kh.getDiachi().equals("") ||
                    kh.getDiachi().equals("") || kh.getLoaikh().equals("")) return false;
        }
        String query = "insert into khachhang(makh, tenkh, ngaysinh, gioitinh, diachi,sdt, loaikh) values (?,?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, kh.getMaKH());
            stmt.setString(2, kh.getTenKH());
            
            stmt.setDate(3, kh.getNgaysinh());
            stmt.setBoolean(4, kh.getGioitinh());
            stmt.setString(5, kh.getDiachi());
            stmt.setString(6, kh.getSDT());
            stmt.setString(7, kh.getLoaikh());
            if(stmt.executeUpdate() > 0) return true;
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }
    
     public void showkhachhang(JTable jT){
        String query = "SELECT kh.makh, kh.tenkh, kh.tenkh, kh.ngaysinh, kh.gioitinh, kh.diachi, kh.sdt, kh.loaikh from khachhang kh";
        
        int c = 0;
        
        Object[] obj = 
                new Object[]{"STT", "makh", "tenkh", "ngaysinh", "gioitinh", "diachi", "sdt","loaikh"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        jT.setModel(tableModel);
        
        ResultSet rs = db.ExcuteGetResultData(query);
        
        try {
            while(rs.next()){
                Object[] item = new Object[8];
                item[0] = c++;
                item[1] = rs.getInt("makh");
                item[2] = rs.getString("tenkh");
                item[3] = rs.getDate("ngaysinh");
                item[4] = rs.getString("gioitinh");
                item[5] = rs.getString("diachi");
                item[6] = rs.getString("sdt");
                item[7] = rs.getString("loaikh");
                
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public boolean isMaKH(int x){
        String truyvan ="select * from khachhang kh where kh.makh=?";
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
    
    public boolean updateNV(KhachHang kh){
        if(kh == null){
            System.out.println("SP is NULL");
            return false;
        } else {
            String truyvan = "UPDATE khachhang kh SET kh.tenkh = ?, kh.ngaysinh = ?, kh.gioitinh = ?,"
                    + "kh.diachi=?,kh.sdt=?, kh.loaikh=? WHERE kh.makh = ?";
            if(!isMaKH(kh.getMaKH())) {
                JOptionPane.showMessageDialog(null, "Khong ton tai maKH nay!");
                return false;
            } else {
                try {
                    PreparedStatement stmt = conn.prepareStatement(truyvan);
                    
                    stmt.setString(1, kh.getTenKH());
                    stmt.setDate(2, kh.getNgaysinh());
                    stmt.setBoolean(3, kh.getGioitinh());
                    
                    stmt.setString(4, kh.getDiachi());
                    stmt.setString(5, kh.getSDT());
                    stmt.setString(6, kh.getLoaikh());
                    stmt.setInt(7, kh.getMaKH());
                    
                    if(stmt.executeUpdate()>0) return true;
                    else return false;
                } catch (SQLException ex) {
                    Logger.getLogger(FuncKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return false;
    }
    
    public KhachHang find(int makh){
        KhachHang kh = new KhachHang(); kh = null;
        String query = "Select * from khachhang kh where kh.makh = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, makh);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                kh.setMaKH(rs.getInt("makh"));
                kh.setTenKH(rs.getString("tenkh"));
                kh.setNgaySinh(rs.getDate("ngaysinh"));
                kh.setGioitinh(rs.getBoolean("gioitinh"));
                kh.setDiachi(rs.getString("diachi"));
                kh.setSDT(rs.getString("sdt"));
                kh.setLoaikh(rs.getString("loaikh"));
     
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kh;
    }
    
    public ResultSet find(String tenkh){
        ResultSet rs = null;
        tenkh = "%"+tenkh+"%";
        String query = "Select * from khachhang kh where kh.tenkh LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tenkh);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
}
