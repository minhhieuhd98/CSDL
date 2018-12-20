/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database_conf.connectDB;
import model.Nhanvien;
import model.Time;
import frm.Trangchu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mhieu
 */
public class FuncNhanVien extends Nhanvien{
    
    private connectDB db = new connectDB();
    private Connection conn = db.connect();
   
    private PreparedStatement stmt;
    
    public boolean themnv(Nhanvien nv){
      
        String query = "INSERT INTO nhanvien(manv, tennv, ngaysinh, gioitinh, ngayvaolam, chucvu, diachi, sdt) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, nv.getMaNV());
            stmt.setString(2, nv.getTenNV());
            stmt.setDate(3, nv.getNgaysinh());
            stmt.setBoolean(4, nv.getGioitinh());
            stmt.setDate(5, nv.getNgayvaolam());
            
            stmt.setString(6, nv.getChucvu());
            stmt.setString(7, nv.getDiachi());
            stmt.setString(8, nv.getSDT());
            if(stmt.executeUpdate()>0) return true;
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean xoanhanvien(int manv){
        String query = "DELETE FROM \"nhanvien\" as \"nv\" WHERE \"manv\" = ?";           
            try {
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, manv);
                stmt.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
    
    public void shownhanvien(JTable jT){
        String query = "SELECT nv.manv, nv.tennv, nv.ngaysinh, nv.gioitinh, nv.ngayvaolam, nv.chucvu, nv.diachi, nv.sdt, nv.ghichu from nhanvien nv order by nv.manv ASC";
        
        int c = 0;
        
        Object[] obj = 
                new Object[]{"STT", "manv", "tennv", "ngaysinh", "gioitinh", "ngayvaolam","chucvu", "diachi", "sdt","ghichu"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        jT.setModel(tableModel);
        
        ResultSet rs = db.ExcuteGetResultData(query);
        
        try {
            while(rs.next()){
                Object[] item = new Object[10];
                item[0] = c++;
                item[1] = rs.getInt("manv");
                item[2] = rs.getString("tennv");
                item[3] = rs.getDate("ngaysinh");
                item[4] = rs.getString("gioitinh");
                item[5] = rs.getDate("ngayvaolam");
                item[6] = rs.getString("chucvu");
                item[7] = rs.getString("diachi");
                item[8] = rs.getString("sdt");
                item[9] = rs.getString("ghichu");
                
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isMaNV(int x){
        String truyvan ="select * from nhanvien nv where nv.manv=?";
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
    
    public boolean updateNV(Nhanvien nv){
        if(nv == null){
            System.out.println("NV is NULL");
            return false;
        } else {
            String truyvan = "UPDATE nhanvien nv SET tennv = ?, ngaysinh = ?, gioitinh = ?,"
                    + "ngayvaolam = ?, chucvu=?, diachi=?,sdt=?, ghichu=? WHERE nv.manv = ?";
            if(!isMaNV(nv.getMaNV())) {
                JOptionPane.showMessageDialog(null, "Khong ton tai manv nay!");
                return false;
            } else {
                try {
                    PreparedStatement stmt = conn.prepareStatement(truyvan);
                    
                    stmt.setString(1, nv.getTenNV());
                    stmt.setDate(2, nv.getNgaysinh());
                    stmt.setBoolean(3, nv.getGioitinh());
                    stmt.setDate(4, nv.getNgayvaolam());
                    stmt.setString(5, nv.getChucvu());
                    stmt.setString(6, nv.getDiachi());
                    stmt.setString(7, nv.getSDT());
                    stmt.setString(8, nv.getGhichu());
                    stmt.setInt(9, nv.getMaNV());
                    
                    if(stmt.executeUpdate()>0) return true;
                    else return false;
                } catch (SQLException ex) {
                    Logger.getLogger(FuncNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return false;
    }
    
    public Nhanvien find(int manv){
        Nhanvien nv = new Nhanvien(); nv = null;
        String query = "Select * from nhanvien nv where nv.manv = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, manv);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                nv.setMaNV(rs.getInt("manv"));
                nv.setTenNV(rs.getString("tennv"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setGioitinh(rs.getBoolean("gioitinh"));
                nv.setNgayvaolam(rs.getDate("ngayvaolam"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setDiachi(rs.getString("diachi"));
                nv.setSDT(rs.getString("sdt"));
                nv.setGhichu(rs.getString("ghichu"));
     
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nv;
    }
    
    public ResultSet find(String tennv){
        ResultSet rs = null;
        tennv = "%"+tennv+"%";
        String query = "Select * from nhanvien nv where nv.tennv LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tennv);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    
    
}
