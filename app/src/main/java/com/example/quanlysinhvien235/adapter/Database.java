package com.example.quanlysinhvien235.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertSinhVien235(String idMSV235,String nameSV235,String idCL235){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO tblop VALUES(?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(0,idMSV235);
        statement.bindString(1,nameSV235);
        statement.bindString(3,idCL235);

        statement.executeInsert();
    }
    public long insertCSV235(String idMSV235,String nameSV235,String idCL235) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idMSV235", idMSV235);
        values.put("nameSinhVien235", nameSV235);
        values.put("idClass235", idCL235);
        return db.insert("SinhVien235", null, values);
    }
    public long insertCL235(String idCL235,String nameCL235) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idClass235", idCL235);
        values.put("nameClass235", nameCL235);
        return db.insert("Class235", null, values);
    }

    public void insertClass235(String idCL235,String nameCL235){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO tblop VALUES(?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(0,idCL235);
        statement.bindString(1,nameCL235);
        statement.executeInsert();
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
