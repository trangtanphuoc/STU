package com.tanphuoc.luanvan.Moudle;

import java.io.Serializable;


public class TramXang implements Serializable {
    private int MaTramXang,MaQuan;
    private String TenTram,Diachi;
    private double lat,lng;
    private double rating;

    public TramXang(int maTramXang, String tenTram, String diachi, double rating, double lat, double lng, int maQuan) {
        MaTramXang = maTramXang;
        MaQuan = maQuan;
        TenTram = tenTram;
        Diachi = diachi;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
    }
    //contructor adapter danh sach
    public TramXang( String tenTram, String diachi,double rating){
        TenTram = tenTram;
        Diachi = diachi;
        this.rating = rating;
    }
    //contructor Khoang Cách
    public TramXang(String tenTram, String diachi, double rating, double lat, double lng) {
        TenTram = tenTram;
        Diachi = diachi;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
    }

    public TramXang() {

    }

    @Override
    public String toString() {
        return "TramXang{" +
                "MaTramATM=" + MaTramXang +
                ", MaQuan=" + MaQuan +
                ", TenTram='" + TenTram + '\'' +
                ", Diachi='" + Diachi + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", rating=" + rating +
                '}';
    }

    public int getMaTramXang() {
        return MaTramXang;
    }

    public void setMaTramXang(int maTramXang) {
        MaTramXang = maTramXang;
    }

    public int getMaQuan() {
        return MaQuan;
    }

    public void setMaQuan(int maQuan) {
        MaQuan = maQuan;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
