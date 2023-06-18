package com.example.hifztracker;
import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {
    }

    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_CLASS = "class";
        public static final String COLUMN_TASKS = "tasks";
    }
}