package com.example.a.assignmnet.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a.assignmnet.Class.SinhVien;

import java.util.ArrayList;

/**
 * Created by A on 7/19/2017.
 */

public class SQLite extends SQLiteOpenHelper {
    private static final String TABLE = "STUDENT";
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_GENDER = "GENDER";
    private static final String KEY_CLASS = "CLASS";
    private static final String KEY_BIRTHDAY = "BIRTHDAY";
    ArrayList<SinhVien> arrayList = new ArrayList<>();

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE + "'");
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +// AUTOINCREMENT
                KEY_NAME + " WARCHAR," +
                KEY_CLASS + " WARCHAR," +
                KEY_GENDER + " WARCHAR," +
                KEY_BIRTHDAY + " WARCHAR)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
    }

    public ArrayList<SinhVien> getData(String name) {
        arrayList.clear();
      String sql= "SELECT * FROM " + TABLE + "";
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String lop = cursor.getString(2);
            String gt = cursor.getString(3);
            String bir = cursor.getString(4);
            arrayList.add(new SinhVien(id, ten, lop,gt, bir));
        }

        return arrayList;
    }

    public ArrayList<SinhVien> getSearch(String name) {
        arrayList.clear();
        String sql= "SELECT * FROM " + TABLE + " WHERE " + KEY_NAME + " LIKE '%"+name+"%'";;
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String lop = cursor.getString(2);
            String gt = cursor.getString(3);
            String bir = cursor.getString(4);
            arrayList.add(new SinhVien(id, ten, lop,gt, bir));
        }

        return arrayList;
    }

//    public String getBook() {
//        String Getbook = "SELECT * FROM " + TABLE + "";
//        return Getbook;
//    }

    public String delete(String id) {
        String Deletebook = "DELETE FROM " + TABLE + " WHERE " + KEY_ID + "='" + id + "'";//Detete row name & id
        return Deletebook;
    }

    public String add(int id, String name, String lop,String gt, String bir) {
        String AddData = "INSERT INTO " + TABLE + " VALUES(" +
                "'" + id + "'," +
                "'" + name + "'," +
                "'" + lop + "'," +
                "'" + gt + "'," +
                "'" + bir + "')";
//        String AddData = "INSERT INTO " + TABLE + " VALUES('" + classBook.id + "','" + classBook.tilte + "','" + classBook.author + "','" + classBook.price + "')";
        return AddData;
    }

    public String update(int id, String name, String lop,String gt, String bir, String idup) {
//        String UpdateData="UPDATE " +TABLE+"   VALUES ('"+id+"', '"+tilte+"', '"+author+"','"+price+"'), WHERE " + KEY_ID + "='" + idup + "'";
        String UpdateData = "UPDATE " + TABLE + " SET " + KEY_ID + "='" + id + "'" +
                " , " + KEY_NAME + "='" + name + "'" +
                " , " + KEY_CLASS + "='" + lop + "'" +
                " , " + KEY_GENDER + "='" + gt + "'" +
                " , " + KEY_BIRTHDAY + "='" + bir + "'" +
                " WHERE ID='" + idup + "'";//Update
        return UpdateData;
    }


}