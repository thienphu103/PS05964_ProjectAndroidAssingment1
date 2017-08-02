package com.example.a.assignmnet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A on 7/19/2017.
 */

public class SQLite extends SQLiteOpenHelper{

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
public void QueryData(String sql) {
    SQLiteDatabase database = getWritableDatabase();
    database.execSQL(sql);
}
public Cursor GetData(String sql){
    SQLiteDatabase database =getReadableDatabase();
    return database.rawQuery(sql,null);
}



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}