package com.example.a.assignmnet.Class;

/**
 * Created by namtn on 25-Jul-17.
 */

public class Lop {
    private String name;
    private String id;
    private int urlHinh;

    public Lop(String name, String id, int urlHinh) {
        this.name = name;
        this.id = id;
        this.urlHinh = urlHinh;
    }

    public Lop(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Lop() {
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

    @Override
    public String toString() {
        return name +"-"+ id ;
    }
}
