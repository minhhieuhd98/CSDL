/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mhieu
 */
public class Sanpham {
    private int masp;
    private String tensp;
    private int loaisp;
    private String hangsx;
    private int gianhap;
    private int giaban;
    private int tonkho;
    private boolean trangthai;
    private String image;
    private String chuthich;
    
    public Sanpham(){
        
    }
    
    public Sanpham(int masp, String tensp, int loaisp, String hangsx, int gianhap, int giaban, int tonkho, boolean status, String linkImage, String chuthich){
        this.masp = masp;
        this.tensp = tensp;
        this.loaisp = loaisp;
        this.hangsx = hangsx;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.tonkho = tonkho;
        this.trangthai = status;
        this.image = linkImage;
        this.chuthich = chuthich;
    }
    
    public void setMasp(int masp){
        this.masp = masp;
    }
    
    public void setTensp(String tensp){
        this.tensp = tensp;
    }
    
    public void setLoaisp(int loai){
        this.loaisp = loai;
    }
    
    public void setHangSX(String hangsx){
        this.hangsx = hangsx;
    }
    
    public void setGiaNhap(int gianhap){
        this.gianhap = gianhap;
    }
    
    public void setGiaBan(int giaban){
        this.giaban = giaban;
    }
    
    public void setTonKho(int tonkho){
        this.tonkho = tonkho;
    }
    
    public void setStatus(boolean x){
        this.trangthai = x;
    }
    
    public void setLinkImage(String link){
        this.image = link;
    }
    
    public void setChuthich(String chuthich){
        this.chuthich = chuthich;
    }
    
    public int getMasp(){
        return this.masp;
    }
    
    public String getTensp(){
        return this.tensp;
    }
    
    public int getLoaisp(){
        return this.loaisp;
    }
    
    public String getHangSX(){
        return this.hangsx;
    }
    
    public int getGiaNhap(){
        return this.gianhap;
    }
    
    public int getGiaBan(){
        return this.giaban;
    }
    
    public int getTonKho(){
        return this.tonkho;
    }
    
    public boolean getStatus(){
        return this.trangthai;
    }
    
    public String getLinkImage(){
        return this.image;
    }
    
    public String getChuthich(){
        return this.chuthich;
    }
}
