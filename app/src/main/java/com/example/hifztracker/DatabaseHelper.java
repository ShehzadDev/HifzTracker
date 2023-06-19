// DatabaseHelper.java
package com.example.hifztracker;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentDB";
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "class";
    private static final String COLUMN_TASK = "task";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_CLASS + " TEXT,"
                + COLUMN_TASK + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());
        values.put(COLUMN_TASK, student.getTasks());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Student searchStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_CLASS, COLUMN_TASK};
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexName = cursor.getColumnIndex(COLUMN_NAME);
            int columnIndexAge = cursor.getColumnIndex(COLUMN_AGE);
            int columnIndexClass = cursor.getColumnIndex(COLUMN_CLASS);
            int columnIndexTask = cursor.getColumnIndex(COLUMN_TASK);

            if (columnIndexId >= 0 && columnIndexName >= 0 && columnIndexAge >= 0 && columnIndexClass >= 0 && columnIndexTask >= 0) {
                int sid = cursor.getInt(columnIndexId);
                String name = cursor.getString(columnIndexName);
                int age = cursor.getInt(columnIndexAge);
                String className = cursor.getString(columnIndexClass);
                String tasks = cursor.getString(columnIndexTask);

                Student student = new Student(sid, name, age, className);
                cursor.close();
                return student;
            }
        }

        cursor.close();
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                @SuppressLint("Range") String className = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS));
                @SuppressLint("Range") String tasks = cursor.getString(cursor.getColumnIndex(COLUMN_TASK));
                students.add(new Student(id, name, age, className));
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return students;
    }
}
