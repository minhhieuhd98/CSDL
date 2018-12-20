/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author mhieu
 */
public class KhachHang {
    private int makh;
    String tenkh, diachi, sdt, loaikh;
    boolean gioitinh;
    Date ngaysinh;
    
    public KhachHang(){
        
    }
    
    public KhachHang(int makh, String tenkh, Date ngaysinh, boolean gioitinh, String diachi, String sdt, String loaikh){
        this.makh = makh;
        this.tenkh = tenkh;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.loaikh = loaikh;
    }
    
    public void setMaKH(int makh){
        this.makh = makh;
    }
    
    public void setTenKH(String tenkh){
        this.tenkh = tenkh;
    }
    
    public void setDiachi(String diachi){
        this.diachi = diachi;
    }    
    
    public void setSDT(String sdt){
        this.sdt = sdt;
    }
    
    public void setLoaikh(String loaikh){
        this.loaikh = loaikh;
    }
    
    public void setNgaySinh(int ngay, int thang, int nam){
        this.ngaysinh = new Date(nam, thang, ngay);
    }
    
    public void setNgaySinh(Date ngaysinh){
        this.ngaysinh = ngaysinh;
    }
    
    public void setGioitinh(boolean x){
        this.gioitinh = x;
    }
    
    public int getMaKH(){
        return this.makh;
    }
    
    public String getTenKH(){
        return this.tenkh;
    }
    
    public String getDiachi(){
        return this.diachi;
    }
    
    public String getSDT(){
        return this.sdt;
    }
    
    public String getLoaikh(){
        return this.loaikh;
    }
    
    public Date getNgaysinh(){
        return this.ngaysinh;
    }
            
    public boolean getGioitinh(){
        return this.gioitinh;
    }
    
}
