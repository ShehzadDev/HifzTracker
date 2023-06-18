package com.example.hifztracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hifztracker.DatabaseContract;
import com.example.hifztracker.DatabaseHelper;
import com.example.hifztracker.R;
import com.example.hifztracker.Student;

public class AddStudent extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextClass;
    private Button buttonSave;

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextClass = findViewById(R.id.editTextClass);
        buttonSave = findViewById(R.id.buttonSave);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });
    }

    private void saveStudent() {
        String name = editTextName.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        String className = editTextClass.getText().toString().trim();

        // Create a new instance of the Student class
        Student student = new Student(0, name, age, className, "");

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.StudentEntry.COLUMN_NAME, student.getName());
        values.put(DatabaseContract.StudentEntry.COLUMN_AGE, student.getAge());
        values.put(DatabaseContract.StudentEntry.COLUMN_CLASS, student.getClassName());
        values.put(DatabaseContract.StudentEntry.COLUMN_TASKS, student.getTasks());

        long newRowId = database.insert(DatabaseContract.StudentEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Student entry saved successfully!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to save student entry.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextName.getText().clear();
        editTextAge.getText().clear();
        editTextClass.getText().clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}


