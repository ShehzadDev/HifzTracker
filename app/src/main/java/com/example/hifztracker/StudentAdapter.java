package com.example.hifztracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        try {
            Student student = studentList.get(position);
            holder.textViewId.setText("ID : "+String.valueOf(student.getId()));
            holder.textViewName.setText("Name : "+student.getName());
            holder.textViewAge.setText("Age : "+String.valueOf(student.getAge()));
            holder.textViewClass.setText("Class : "+student.getClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewAge, textViewClass,textViewId;

        public StudentViewHolder(View itemView) {
            super(itemView);
            textViewId=itemView.findViewById(R.id.textViewId);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewClass = itemView.findViewById(R.id.textViewClass);
        }
    }
}
