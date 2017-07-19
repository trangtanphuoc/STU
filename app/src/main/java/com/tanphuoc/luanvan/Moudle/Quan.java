package com.tanphuoc.luanvan.Moudle;


import java.io.Serializable;

public class Quan  implements Serializable {
    private int id;
    private String TenQuan;

    public Quan(int id, String tenQuan) {
        this.id = id;
        TenQuan = tenQuan;
    }

    public Quan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }

    @Override
    public String toString() {
        return  TenQuan;
    }
}
