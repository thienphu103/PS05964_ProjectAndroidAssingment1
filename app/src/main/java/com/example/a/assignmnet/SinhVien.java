package com.example.a.assignmnet;

/**
 * Created by namtn on 25-Jul-17.
 */

public class SinhVien {
    private String name;
    private String id;
    private int urlHinh;

    public SinhVien(String name, String id, int urlHinh) {
        this.name = name;
        this.id = id;
        this.urlHinh = urlHinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUrlHinh() {
        return urlHinh;
    }

    public void setUrlHinh(int urlHinh) {
        this.urlHinh = urlHinh;
    }
}
