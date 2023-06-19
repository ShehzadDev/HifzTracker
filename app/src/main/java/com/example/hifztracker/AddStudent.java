// AddStudent.java

package com.example.hifztracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    EditText editTextId, editTextName, editTextAge, editTextClass, editTextTask;
    Button buttonSubmit;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Find views by their IDs
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextClass = findViewById(R.id.editTextClass);
        buttonSubmit = findViewById(R.id.buttonSave);

        dbHelper = new DatabaseHelper(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextId.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String className = editTextClass.getText().toString().trim();

                if (name.isEmpty() || id.isEmpty() || age.isEmpty() || className.isEmpty()) {
                    Toast.makeText(AddStudent.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student(Integer.parseInt(id), name, Integer.parseInt(age), className);
                dbHelper.insertStudent(student);

                Toast.makeText(AddStudent.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

                // Clear the input fields
                editTextId.setText("");
                editTextName.setText("");
                editTextAge.setText("");
                editTextClass.setText("");
            }
        });
    }
}
