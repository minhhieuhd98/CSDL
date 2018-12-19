/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database_conf.connectDB;
import frm.Sanpham;
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
public class FuncSanPham extends Sanpham{
    private connectDB db = new connectDB();
    private Connection conn = db.connect();
   
    private PreparedStatement stmt;
    
    public void showsanpham(JTable jT ) {
        String query = "SELECT sp.masp, sp.tensp, sp.loaisp, sp.hangsx, sp.gianhap, sp.giaban,sp.tonkho, sp.trangthai, sp.image, sp.chuthich "
                + "FROM sanpham sp INNER JOIN loaisanpham lsp ON lsp.id=sp.loaisp ORDER BY masp ASC" ;
        
        int c = 0;
        
        Object[] obj = 
                new Object[]{"STT", "masp", "tensp", "loaisp", "hangsx", "gianhap", "giaban","tonkho", "trangthai","image","chuthich"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        jT.setModel(tableModel);
        
        ResultSet rs = db.ExcuteGetResultData(query);
        
        try {
            while(rs.next()) {
                c++;
                Object[] item = new Object[11];
                item[0] = c;
                item[1] = rs.getInt("masp");
                item[2] = rs.getString("tensp");
                item[3] = rs.getString("loaisp");
                item[4] = rs.getString("hangsx"); // mau sua cai nay
                item[5] = rs.getInt("gianhap");
                item[6] = rs.getInt("giaban");
                item[7] = rs.getInt("tonkho");
                item[8] = rs.getString("trangthai");
                item[9] = rs.getString("image");
                item[10] = rs.getString("chuthich");
                //System.out.println(item[1]);
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xoasanpham(String masp){
        if(masp.equals("")) {
            JOptionPane.showMessageDialog(null, "Mời bạn chọn mã sản phẩm cho mình trước khi xóa nhé!");
        } else {
            //Thuc hien chuc nang xoa
            String query = "DELETE FROM \"sanpham\" as \"sp\" WHERE \"masp\" = ?";           
            
            try {
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.valueOf(masp));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Đã xóa thành công");
            } catch (SQLException ex) {
                Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Da xay ra loi! Co the khong ton tai masp ban chon");
            }
            
        }
    }
    
    public boolean themsp(Sanpham sp){
        if(sp == null)
        {
            JOptionPane.showMessageDialog(null, "Bạn đang nhập thiếu thông tin! Vui lòng kiểm tra lại đầy đủ trước khi thêm!");
        }
        else {
           
            try {
                String cautruyvan = "insert into \"sanpham\"(\"masp\", \"tensp\", \"loaisp\", \"hangsx\", \"gianhap\", \"giaban\",\"tonkho\",\"trangthai\",\"image\",\"chuthich\")"
                        + "values(?,?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement stmt = conn.prepareStatement(cautruyvan);
                
                stmt.setInt(1, sp.getMasp());
                stmt.setString(2, sp.getTensp());
                stmt.setInt(3, sp.getLoaisp());
                stmt.setString(4, sp.getHangSX());
                stmt.setInt(5, sp.getGiaBan());
                stmt.setInt(6, sp.getGiaNhap());
                stmt.setInt(7, sp.getTonKho());
                stmt.setBoolean(8, sp.getStatus());
                stmt.setString(9, "xxxxxx");
                stmt.setString(10, sp.getChuthich());
                
                if(!isMasp(sp.getMasp())){
                    if(stmt.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "Đã thêm!");
                        return true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Đã tồn tại mã sp trên");
                    return false;
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Them san pham that bai!");
            }
            
        } 
        return false;
    }
    
    public boolean isMasp(int x){
        String truyvan ="select * from sanpham sp where sp.masp=?";
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
    
    public boolean updateSP(Sanpham sp){
        if(sp == null){
            System.out.println("SP is NULL");
            return false;
        } else {
            String truyvan = "UPDATE sanpham sp SET sp.tensp = ?, sp.loaisp = ?, sp.hangsx = ?"
                    + "sp.gianhap = ?, sp.giaban=?,sp.image=?,sp.chuthich=? WHERE sp.masp = ?";
            if(!isMasp(sp.getMasp())) {
                JOptionPane.showMessageDialog(null, "Khong ton tai masp nay!");
                return false;
            } else {
                try {
                    PreparedStatement stmt = conn.prepareStatement(truyvan);
                    
                    stmt.setString(1, sp.getTensp());
                    stmt.setInt(2, sp.getLoaisp());
                    stmt.setString(3, sp.getHangSX());
                    stmt.setInt(4, sp.getGiaNhap());
                    stmt.setInt(5, sp.getGiaBan());
                    stmt.setString(6, sp.getLinkImage());
                    stmt.setString(7, sp.getChuthich());
                    stmt.setInt(8, sp.getMasp());
                    if(stmt.executeUpdate()>0) return true;
                    else return false;
                } catch (SQLException ex) {
                    Logger.getLogger(FuncSanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return false;
    }
    
    public Sanpham find(int masp){
        Sanpham sp = new Sanpham(); sp = null;
        String query = "Select * from sanpham sp where sp.masp = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, masp);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                sp.setMasp(rs.getInt("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setLoaisp(rs.getInt("loaisp"));
                sp.setHangSX(rs.getString("hangsx"));
                sp.setGiaNhap(rs.getInt("gianhap"));
                sp.setGiaBan(rs.getInt("giaban"));
                sp.setTonKho(rs.getInt("tonkho"));
                sp.setStatus(rs.getBoolean("trangthai"));
                sp.setLinkImage(rs.getString("image"));
                sp.setChuthich(rs.getString("chuthich"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sp;
    }
    
    public ResultSet find(String tensp){
        ResultSet rs = null;
        tensp = "%" + tensp + "%";
        String query = "Select * from sanpham sp where sp.tensp LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tensp);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
}
