package com.tanphuoc.luanvan.Moudle;


import java.io.Serializable;

public class NganHang implements Serializable {
    private int id;
    private String TenNH;

    public NganHang() {
    }

    public NganHang(int id, String tenNH) {
        this.id = id;
        TenNH = tenNH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNH() {
        return TenNH;
    }

    public void setTenNH(String tenNH) {
        TenNH = tenNH;
    }

    @Override
    public String toString() {
        return "Ngân Hàng : " + TenNH ;
    }
}
