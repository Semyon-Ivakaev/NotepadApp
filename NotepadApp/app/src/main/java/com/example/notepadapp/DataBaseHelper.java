package com.example.notepadapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getWritableDatabase();
    public static final String DATABASE_NAME = "Notepad.db";
    public static final String TABLE_NAME = "Notes_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOTE";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOTE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String Note){

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Note);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String id, String Note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Note);
        long result = db.update(TABLE_NAME, contentValues, "ID =?", new String[]{id});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Integer deleteData(String id) {
        int result = db.delete(TABLE_NAME, "ID =?", new String[]{id});
        return result;
    }

    public Cursor getAllData() {
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        if (db != null) {
            res = db.rawQuery("Select * from " + TABLE_NAME, null);
        }
        return res;
    }
}
