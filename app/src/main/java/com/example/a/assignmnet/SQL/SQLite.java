package com.example.a.assignmnet.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.a.assignmnet.Class.Lop;
import com.example.a.assignmnet.Class.Resgiter;
import com.example.a.assignmnet.Class.SinhVien;

import java.util.ArrayList;

/**
 * Created by A on 7/19/2017.
 */

public class SQLite extends SQLiteOpenHelper {
    private static final String TABLE = "STUDENT";
    private static final String KEY_ID_TABLE = "ID_TABLE";
    private static final String KEY_ID = "ID";
    private static final String KEY_IMAGE = "IMAGE";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_GENDER = "GENDER";
    private static final String KEY_CLASS = "CLASS";
    private static final String KEY_BIRTHDAY = "BIRTHDAY";

    private static final String TABLE_CLASS = "CLASS";
    private static final String KEY_ID_TABLE_CLASS = "ID_TABLE_CLASS";
    private static final String KEY_ID_CLASS = "ID_CLASS";
    private static final String KEY_NAME_CLASS = "NAME_CLASS";

    private static final String TABLE_REGISTER = "REGISTER";
    private static final String KEY_ID_REGISTER = "ID_REGISTER";
    private static final String KEY_USER = "USER";
    private static final String KEY_PASS = "PASS";

    ArrayList<SinhVien> arrayList = new ArrayList<>();
    ArrayList<Lop> arrayListClass = new ArrayList<>();
    ArrayList<Resgiter> arrayListRe = new ArrayList<>();

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
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_CLASS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_REGISTER + "'");
        String CREATE_TABLE_STUDENT = "CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_ID_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT," +//
                KEY_ID + " INTEGER ," +//AUTOINCREMENT
                KEY_IMAGE + " BLOB," +
                KEY_NAME + " VARCHAR," +
                KEY_CLASS + " VARCHAR," +
                KEY_GENDER + " VARCHAR," +
                KEY_BIRTHDAY + " VARCHAR)";

        String CREATE_TABLE_CLASS = "CREATE TABLE IF NOT EXISTS " + TABLE_CLASS + "(" +
                KEY_ID_TABLE_CLASS + " INTEGER PRIMARY KEY ," +//AUTOINCREMENT
                KEY_ID_CLASS + " VARCHAR," +
                KEY_NAME_CLASS + " VARCHAR)";

        String CREATE_TABLE_REGISTER = "CREATE TABLE IF NOT EXISTS " + TABLE_REGISTER + "(" +
                KEY_ID_REGISTER + " INTEGER PRIMARY KEY AUTOINCREMENT ," +//
                KEY_USER + " VARCHAR," +
                KEY_PASS + " VARCHAR)";
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_CLASS);
        db.execSQL(CREATE_TABLE_REGISTER);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
    }

    public ArrayList<SinhVien> getData(String name) {
        arrayList.clear();
        String sql = "SELECT * FROM " + TABLE + "";
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id_table = cursor.getString(0);
            String id = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String ten = cursor.getString(3);
            String lop = cursor.getString(4);
            String gt = cursor.getString(5);
            String bir = cursor.getString(6);
            arrayList.add(new SinhVien(id_table,id,image, ten, lop, gt, bir));
        }

        return arrayList;
    }

    public ArrayList<SinhVien> getSearch(String name) {
        arrayList.clear();
        String sql = "SELECT * FROM " + TABLE + " WHERE " + KEY_NAME + " LIKE '%" + name + "%'";
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id_table = cursor.getString(0);
            String id = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String ten = cursor.getString(3);
            String lop = cursor.getString(4);
            String gt = cursor.getString(5);
            String bir = cursor.getString(6);
            arrayList.add(new SinhVien(id_table,id,image, ten, lop, gt, bir));
        }
        return arrayList;
    }

//    public String getBook() {
//        String Getbook = "SELECT * FROM " + TABLE + "";
//        return Getbook;
//    }

    public String delete(String id) {
        String Deletebook = "DELETE FROM " + TABLE + " WHERE " + KEY_ID_TABLE + "='" + id + "'";//Detete row name & id
        return Deletebook;
    }

//    public String addStu(String id,byte[] anh ,String name, String lop, String gt, String bir) {
//        String AddData = "INSERT INTO " + TABLE + " VALUES(" +
//                "null," +
//                "'" + id + "'," +
//                "'" + anh + "'," +
//                "'" + name + "'," +
//                "'" + lop + "'," +
//                "'" + gt + "'," +
//                "'" + bir + "')";
////        String AddData = "INSERT INTO " + TABLE + " VALUES('" + classBook.id + "','" + classBook.tilte + "','" + classBook.author + "','" + classBook.price + "')";
//        return AddData;
//    }
    //do dùng byte ten hk dung phuong thuc insert sql duoc

    public void addStu(String id,byte[] anh ,String name, String lop, String gt, String bir) {
        String sql = "INSERT INTO STUDENT VALUES (NULL, ?, ?, ?,?,?,?)";
        SQLiteDatabase database = getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, id);
        statement.bindBlob(2, anh);
        statement.bindString(3, name);
        statement.bindString(4, lop);
        statement.bindString(5, gt);
        statement.bindString(6, bir);
        statement.executeInsert();
    }

    public void updateStu(String id,byte[] anh ,String name, String lop, String gt, String bir, String idup) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE " + TABLE + " SET "

                + KEY_ID + "= ?" +
                " , " + KEY_IMAGE + "= ?" +
                " , " + KEY_NAME + "= ?" +
                " , " + KEY_CLASS + "= ?" +
                " , " + KEY_GENDER + "= ?" +
                " , " + KEY_BIRTHDAY + "= ?" +
                " WHERE ID_TABLE='" + idup + "'";//Update
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, id);
        statement.bindBlob(2, anh);
        statement.bindString(3, name);
        statement.bindString(4, lop);
        statement.bindString(5, gt);
        statement.bindString(6, bir);
        statement.execute();
        database.close();
    }

//    public String update(String id,byte[] anh ,String name, String lop, String gt, String bir, String idup) {
////        String UpdateData="UPDATE " +TABLE+"   VALUES ('"+id+"', '"+tilte+"', '"+author+"','"+price+"'), WHERE " + KEY_ID + "='" + idup + "'";
//        String UpdateData = "UPDATE " + TABLE + " SET " + KEY_ID_TABLE + "='" + id + "'" +
////                "null," +
//                " , " + KEY_ID + "='" + id + "'" +
//                " , " + KEY_IMAGE + "='" + anh + "'" +
//                " , " + KEY_NAME + "='" + name + "'" +
//                " , " + KEY_CLASS + "='" + lop + "'" +
//                " , " + KEY_GENDER + "='" + gt + "'" +
//                " , " + KEY_BIRTHDAY + "='" + bir + "'" +
//                " WHERE ID='" + idup + "'";//Update
//        return UpdateData;
//    }

    //TABLE CLASS
    public ArrayList<Lop> getDataClass(String name) {
        arrayListClass.clear();
        String sql = "SELECT * FROM " + TABLE_CLASS + "";
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id_table = cursor.getString(0);
            String id = cursor.getString(1);
            String ten = cursor.getString(2);

            arrayListClass.add(new Lop(id_table, ten));
        }

        return arrayListClass;
    }

    public ArrayList<Lop> getDataClassSpinner(String name) {
        arrayListClass.clear();
        String sql = "SELECT * FROM " + TABLE_CLASS + "";
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id_table = cursor.getString(0);
            String id = cursor.getString(1);
            String ten = cursor.getString(2);

            arrayListClass.add(new Lop(id_table, ten));
        }

        return arrayListClass;
    }

    public ArrayList<Lop> getSearchClass(String name) {
        arrayList.clear();
        String sql = "SELECT * FROM " + TABLE_CLASS + " WHERE " + KEY_ID_TABLE_CLASS+ " LIKE '%" + name + "%'";
        ;
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id_table = cursor.getString(0);
            String id = cursor.getString(1);
            String ten = cursor.getString(2);
            arrayListClass.add(new Lop(id_table, ten));
        }

        return arrayListClass;
    }

//    public String getBook() {
//        String Getbook = "SELECT * FROM " + TABLE + "";
//        return Getbook;
//    }

    public String deleteClass(String id) {
        String Deletebook = "DELETE FROM " + TABLE_CLASS + " WHERE " + KEY_ID_TABLE_CLASS + "='" + id + "'";//Detete row name & id
        return Deletebook;
    }

    public String addClass(String id, String name) {
        String AddData = "INSERT INTO " + TABLE_CLASS + " VALUES(" +

                "'" + id + "'," +
                "null," +
                "'" + name + "')";
//        String AddData = "INSERT INTO " + TABLE + " VALUES('" + classBook.id + "','" + classBook.tilte + "','" + classBook.author + "','" + classBook.price + "')";
        return AddData;
    }

    public String updateClass(String id, String name, String idup) {
//        String UpdateData="UPDATE " +TABLE+"   VALUES ('"+id+"', '"+tilte+"', '"+author+"','"+price+"'), WHERE " + KEY_ID + "='" + idup + "'";
        String UpdateData = "UPDATE " + TABLE_CLASS + " SET " + KEY_ID_TABLE_CLASS + "='" + id + "'" +
//                "null," +
                " , " + KEY_NAME_CLASS + "='" + name + "'" +
                " WHERE " + KEY_ID_CLASS + "='" + idup + "'";//Update
        return UpdateData;
    }


    public ArrayList<Resgiter> getDataRe(String name) {
        arrayListRe.clear();
        String sql = "SELECT * FROM " + TABLE_REGISTER + "";
        Cursor cursor = GetData(sql);
        while (cursor.moveToNext()) {
            String id_table = cursor.getString(0);
            String id = cursor.getString(1);
            String ten = cursor.getString(2);
            arrayListRe.add(new Resgiter(id, ten));

        }

        return arrayListRe;
    }

    public String addRe(String id, String name) {
        String AddData = "INSERT INTO " + TABLE_REGISTER + " VALUES(" +
                "null," +
                "'" + id + "'," +

                "'" + name + "')";
//        String AddData = "INSERT INTO " + TABLE + " VALUES('" + classBook.id + "','" + classBook.tilte + "','" + classBook.author + "','" + classBook.price + "')";
        return AddData;
    }


}