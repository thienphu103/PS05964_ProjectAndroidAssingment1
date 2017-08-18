package com.example.a.assignmnet.Class;

/**
 * Created by namtn on 25-Jul-17.
 */

public class SinhVien {
    public String id;
    public String id_table;
    public String name;
    public String lop;
    public String gender;
    public String birthday;
    public byte[] image;

    public SinhVien(String id_table) {
        this.id_table = id_table;
    }

    public String getId_table() {
        return id_table;
    }

    public void setId_table(String id_table) {
        this.id_table = id_table;
    }

    public SinhVien(String id_table, String id, byte[] image , String name, String lop, String gender, String birthday) {
        this.id_table = id_table;
        this.id = id;
        this.image = image;
        this.lop = lop;
        this.gender = gender;
        this.birthday = birthday;
        this.name = name;
    }

//        public SinhVien( byte[] image ,String name, String lop, String gender, String birthday) {
//        this.image = image;
//        this.lop = lop;
//        this.gender = gender;
//        this.birthday = birthday;
//        this.name = name;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public SinhVien() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
