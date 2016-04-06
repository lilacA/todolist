package com.example.lilaca01.ex2_mobile;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lilaca01 on 25/03/2016.
 */
public class todo_db extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "todo_db";
    public static final String TABLE_NAME = "todo";
    public static final String FIRST_COL_NAME = "_id";
    public static final String SECOND_COL_NAME = "title";
    public static final String THIRD_COL_NAME = "due";

    //Constructor
    public todo_db(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + FIRST_COL_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SECOND_COL_NAME + " TEXT, " + THIRD_COL_NAME + " LONG);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, long date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SECOND_COL_NAME, title);
        contentValues.put(THIRD_COL_NAME, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        return true;
    }
}
