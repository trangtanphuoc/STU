package com.tanphuoc.luanvan.Moudle;


import java.io.Serializable;

public class TramATM implements Serializable{
    private int MaTramATM,MaQuan,MaNH;
    private String TenTram,Diachi;
    private double lat,lng;
    private double rating;

    public TramATM(int maTramATM, String tenTram, String diachi, double rating, double lat, double lng, int maQuan, int maNH) {
        MaTramATM = maTramATM;
        MaQuan = maQuan;
        MaNH = maNH;
        TenTram = tenTram;
        Diachi = diachi;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }
    //contructor adapter danh sach
    public TramATM(String tenTram, String diachi,double rating,int maNH){
        TenTram = tenTram;
        Diachi = diachi;
        MaNH = maNH;
        this.rating = rating;
    }
    //contructor Khoang Cách
    public TramATM(String tenTram, String diachi, double rating, double lat, double lng) {
        TenTram = tenTram;
        Diachi = diachi;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "TramATM{" +
                "MaTramATM=" + MaTramATM +
                ", MaQuan=" + MaQuan +
                ", MaNH=" + MaNH +
                ", TenTram='" + TenTram + '\'' +
                ", Diachi='" + Diachi + '\'' +
                ", rating=" + rating +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public TramATM() {
    }

    public int getMaTramATM() {
        return MaTramATM;
    }

    public void setMaTramATM(int maTramATM) {
        MaTramATM = maTramATM;
    }

    public int getMaQuan() {
        return MaQuan;
    }

    public void setMaQuan(int maQuan) {
        MaQuan = maQuan;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }

    public String getTenTram() {
        return TenTram;
    }

    public void setTenTram(String tenTram) {
        TenTram = tenTram;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
