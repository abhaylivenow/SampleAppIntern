package com.example.sampleacapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String dbname="DB_AC";

    public DatabaseManager(@Nullable Context context){
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table AC_DETAIL_TABLE ( id integer PRIMARY KEY AUTOINCREMENT, MODEL text, SERIAL_NUMBER text, INSTALLED_PLACE text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry="DROP TABLE IF EXISTS AC_DETAIL_TABLE";
        db.execSQL(qry);
        onCreate(db);
    }

    public String addRecord(String model, String serialNumber, String installedPlace){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("MODEL",model);
        cv.put("SERIAL_NUMBER",serialNumber);
        cv.put("INSTALLED_PLACE",installedPlace);

        float res = db.insert("AC_DETAIL_TABLE",null,cv);

        if(res == -1)
            return "Failed";
        else
            return  "Successfully inserted";
    }

    public Cursor readAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String qry = "select * from AC_DETAIL_TABLE order by id desc";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;
    }

    public void delete(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from AC_DETAIL_TABLE");
    }
}
