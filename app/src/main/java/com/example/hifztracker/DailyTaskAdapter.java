package com.example.hifztracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyTaskAdapter extends RecyclerView.Adapter<DailyTaskAdapter.ViewHolder> {

    private List<DailyTask> dailyTaskList;

    public DailyTaskAdapter(List<DailyTask> dailyTaskList) {
        this.dailyTaskList = dailyTaskList;
    }

    public void setTaskList(List<DailyTask> taskList) {
        this.dailyTaskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyTask dailyTask = dailyTaskList.get(position);
        holder.textViewStudentId.setText("ID: " + String.valueOf(dailyTask.getStudentId()));
        holder.textViewSabaq.setText("Sabaq : "+dailyTask.getSabaq());
        holder.textViewSabaqi.setText("Sabaqi : "+dailyTask.getSabaqi());
        holder.textViewManzil.setText("Manzil : "+dailyTask.getManzil());
    }

    @Override
    public int getItemCount() {
        return dailyTaskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStudentId;
        TextView textViewSabaq;
        TextView textViewSabaqi;
        TextView textViewManzil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStudentId = itemView.findViewById(R.id.textViewId);
            textViewSabaq = itemView.findViewById(R.id.textViewSabaq);
            textViewSabaqi = itemView.findViewById(R.id.textViewSabaqi);
            textViewManzil = itemView.findViewById(R.id.textViewManzil);
        }
    }
}