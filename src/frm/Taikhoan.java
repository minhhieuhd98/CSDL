/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

/**
 *
 * @author mhieu
 */
public class Taikhoan extends Nhanvien{
    private String tendn, matkhau;
    private boolean quyen;
    
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
