package com.example.admin.qlnvcustomlistviewdemo;

/**
 * Created by Admin on 7/6/2016.
 */
public class Nhanvien {
    private String ten;
    private String lop;

    public Nhanvien(String ten, String lop) {
        this.ten = ten;
        this.lop = lop;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
