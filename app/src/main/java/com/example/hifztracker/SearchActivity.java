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
public class SearchActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button searchButton, viewAllButton;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student);

        // Initialize views
        editTextSearch = findViewById(R.id.editTextSearchId);
        searchButton = findViewById(R.id.buttonSearch);
        viewAllButton = findViewById(R.id.buttonDisplayAll);
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextSearch.getText().toString().trim();
                if (!searchQuery.isEmpty()) {
                    try {
                        int studentId = Integer.parseInt(searchQuery);
                        searchStudents(studentId);
                    } catch (NumberFormatException e) {
                        Toast.makeText(SearchActivity.this, "Invalid student ID format", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(SearchActivity.this, "An error occurred during the search", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SearchActivity.this, "Please enter a student ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    displayAllStudents();
                } catch (Exception e) {
                    Toast.makeText(SearchActivity.this, "An error occurred while displaying students", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void searchStudents(int studentId) {
        try {
            List<Student> students = dbHelper.searchStudent(studentId);
            if (!students.isEmpty()) {
                studentList.clear();
                studentList.addAll(students);
                studentAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred during the search", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void displayAllStudents() {
        try {
            List<Student> allStudents = dbHelper.getAllStudents();
            if (!allStudents.isEmpty()) {
                studentList.clear();
                studentList.addAll(allStudents);
                studentAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "No students found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred while displaying students", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
