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
    private static final String TABLE_TASKS = "tasks";
    private static final String COLUMN_TASK_ID = "task_id";
    private static final String COLUMN_TASK_STUDENT_ID = "student_id";
    private static final String COLUMN_SABAQ = "sabaq";
    private static final String COLUMN_SABAQI = "sabaqi";
    private static final String COLUMN_MANZIL = "manzil";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_CLASS + " TEXT"
                + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);

        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + COLUMN_TASK_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TASK_STUDENT_ID + " INTEGER,"
                + COLUMN_SABAQ + " TEXT,"
                + COLUMN_SABAQI + " TEXT,"
                + COLUMN_MANZIL + " TEXT"
                + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Student> searchStudent(int id) {
        List<Student> students = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_CLASS};
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexName = cursor.getColumnIndex(COLUMN_NAME);
            int columnIndexAge = cursor.getColumnIndex(COLUMN_AGE);
            int columnIndexClass = cursor.getColumnIndex(COLUMN_CLASS);

            do {
                if (columnIndexId >= 0 && columnIndexName >= 0 && columnIndexAge >= 0 && columnIndexClass >= 0) {
                    int sid = cursor.getInt(columnIndexId);
                    String name = cursor.getString(columnIndexName);
                    int age = cursor.getInt(columnIndexAge);
                    String className = cursor.getString(columnIndexClass);

                    students.add(new Student(sid, name, age, className));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return students;
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
                students.add(new Student(id, name, age, className));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return students;
    }

    public boolean insertTaskDetails(int selectedStudentId, String sabaq, String sabaqi, String manzil) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_STUDENT_ID, selectedStudentId);
        values.put(COLUMN_SABAQ, sabaq);
        values.put(COLUMN_SABAQI, sabaqi);
        values.put(COLUMN_MANZIL, manzil);

        long result = db.insert(TABLE_TASKS, null, values);
        db.close();

        return result != -1;
    }

    public List<DailyTask> getAllDailyTasks(int studentid) {
        List<DailyTask> dailyTasks = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_TASK_ID));
                @SuppressLint("Range") int studentId = cursor.getInt(cursor.getColumnIndex(COLUMN_TASK_STUDENT_ID));
                @SuppressLint("Range") String sabaq = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQ));
                @SuppressLint("Range") String sabaqi = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQI));
                @SuppressLint("Range") String manzil = cursor.getString(cursor.getColumnIndex(COLUMN_MANZIL));
                dailyTasks.add(new DailyTask(id, studentId, sabaq, sabaqi, manzil));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dailyTasks;
    }
}