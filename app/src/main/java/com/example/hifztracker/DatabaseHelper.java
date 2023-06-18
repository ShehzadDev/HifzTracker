package com.example.hifztracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hifztracker.DatabaseContract;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.StudentEntry.TABLE_NAME + " (" +
                    DatabaseContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.StudentEntry.COLUMN_NAME + " TEXT," +
                    DatabaseContract.StudentEntry.COLUMN_AGE + " INTEGER," +
                    DatabaseContract.StudentEntry.COLUMN_CLASS + " TEXT," +
                    DatabaseContract.StudentEntry.COLUMN_TASKS + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.StudentEntry.TABLE_NAME);
        onCreate(db);
    }
}

