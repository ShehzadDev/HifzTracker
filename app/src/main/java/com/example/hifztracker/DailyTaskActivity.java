// DailyTaskActivity.java

package com.example.hifztracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DailyTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DailyTaskAdapter dailyTaskAdapter;
    private List<Student> studentList;
    private DatabaseHelper dbHelper;
    private EditText editTextSabaq;
    private EditText editTextSabaqi;
    private EditText editTextManzil;
    private Button buttonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_task);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        editTextSabaq = findViewById(R.id.editTextSabaq);
        editTextSabaqi = findViewById(R.id.editTextSabaqi);
        editTextManzil = findViewById(R.id.editTextManzil);
        buttonAddTask = findViewById(R.id.buttonAddTask);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();
        dailyTaskAdapter = new DailyTaskAdapter(studentList);
        recyclerView.setAdapter(dailyTaskAdapter);

        // Fetch all student records from the database
        fetchStudentRecords();

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered task details
                String sabaq = editTextSabaq.getText().toString().trim();
                String sabaqi = editTextSabaqi.getText().toString().trim();
                String manzil = editTextManzil.getText().toString().trim();

                // Get the selected student's ID
                int selectedStudentId = dailyTaskAdapter.getSelectedStudentId();

                // Insert task details into the tasks table
                boolean isTaskInserted = dbHelper.insertTaskDetails(selectedStudentId, sabaq, sabaqi, manzil);

                if (isTaskInserted) {
                    Toast.makeText(DailyTaskActivity.this, "Task details added successfully", Toast.LENGTH_SHORT).show();
                    clearTaskFields();
                } else {
                    Toast.makeText(DailyTaskActivity.this, "Failed to add task details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchStudentRecords() {
        try {
            studentList = dbHelper.getAllStudents();
            dailyTaskAdapter.setStudents(studentList);
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred while fetching student records", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void clearTaskFields() {
        editTextSabaq.setText("");
        editTextSabaqi.setText("");
        editTextManzil.setText("");
    }
}