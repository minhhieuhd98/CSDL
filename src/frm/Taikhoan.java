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
public class Taikhoan extends Nhanvien{
    private String tendn, matkhau;
    private boolean quyen;
    private int manv;
    
    public Taikhoan(){
        
    }
    
    public Taikhoan(int manv, String tendn, String matkhau, boolean quyen){
        this.manv = manv; this.tendn = tendn; this.matkhau = matkhau; this.quyen = quyen;
    }

    public Taikhoan(int manv, String tennv, Date ngaysinh, boolean gioitinh, Date ngayvaolam, String chucvu, String diachi, String sdt) {
        super(manv, tennv, ngaysinh, gioitinh, ngayvaolam, chucvu, diachi, sdt);
    }
    
    public void setMaNV(int manv){
        this.manv = manv;
    }
    
    public int getMaNV(){
        return this.manv;
    }

    public void setTenDN(String tendn){
        this.tendn = tendn;
    }
    
    public void setMatkhau(String matkhau){
        this.matkhau = matkhau;
    }
    
    public void setQuyen(boolean quyen){
        this.quyen = quyen;
    }
    
    public String getTenDN(){
        return this.tendn;
    }
    
    public String getMatKhau(){
        return this.matkhau;
    }
    
    public boolean getQuyen(){
        return this.quyen;
    }
    
}
