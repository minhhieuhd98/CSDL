/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import java.sql.Date;



/**
 *
 * @author mhieu
 */
public class Nhanvien {
    private int manv;
    private String tennv, diachi, sdt, ghichu, chucvu;
    private boolean gioitinh;

    private Date ngaysinh;
    
    private Date ngayvaolam;
    
    public void setMaNV(int manv){
        this.manv = manv;
    }
    
    public void setTenNV(String tennv){
        this.tennv = tennv;
    }
    
    public void setDiachi(String diachi){
        this.diachi = diachi;
    }    
    
    public void setSDT(String sdt){
        this.sdt = sdt;
    }
    
    public void setGhichu(String ghichu){
        this.ghichu = ghichu;
    }
    
    public void setChucvu(String chucvu){
        this.chucvu = chucvu;
    }
    
    public void setNgaySinh(int ngay, int thang, int nam){
        this.ngaysinh = new Date(nam, thang, ngay);
    }
    
    public void setNgayvaolam(int ngay, int thang, int nam){
        this.ngayvaolam = new Date(nam, thang, ngay);
    }
    
    public void setGioitinh(boolean x){
        this.gioitinh = x;
    }
    
    public int getMaNV(){
        return this.manv;
    }
    
    public String getTenNV(){
        return this.tennv;
    }
    
    public String getDiachi(){
        return this.diachi;
    }
    
    public String getSDT(){
        return this.sdt;
    }
    
    public String getGhichu(){
        return this.ghichu;
    }
    
    public String getChucvu(){
        return this.chucvu;
    }
    
    public Date getNgaysinh(){
        return this.ngaysinh;
    }
    
    public Date getNgayvaolam(){
        return this.ngayvaolam;
    }
            
    public boolean getGioitinh(){
        return this.gioitinh;
    }
}
