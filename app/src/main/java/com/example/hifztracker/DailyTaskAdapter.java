// DailyTaskAdapter.java

package com.example.hifztracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyTaskAdapter extends RecyclerView.Adapter<DailyTaskAdapter.DailyTaskViewHolder> {

    private List<DailyTask> dailyTaskList;

    public DailyTaskAdapter(List<DailyTask> dailyTaskList) {
        this.dailyTaskList = dailyTaskList;
    }

    @NonNull
    @Override
    public DailyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_task, parent, false);
        return new DailyTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyTaskViewHolder holder, int position) {
        DailyTask dailyTask = dailyTaskList.get(position);
        holder.textViewSabaq.setText(dailyTask.getSabaq());
        holder.textViewSabaqi.setText(dailyTask.getSabaqi());
        holder.textViewManzil.setText(dailyTask.getManzil());
    }

    @Override
    public int getItemCount() {
        return dailyTaskList.size();
    }

    public class DailyTaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSabaq;
        TextView textViewSabaqi;
        TextView textViewManzil;

        public DailyTaskViewHolder(View itemView) {
            super(itemView);
            textViewSabaq = itemView.findViewById(R.id.textViewSabaq);
            textViewSabaqi = itemView.findViewById(R.id.textViewSabaqi);
            textViewManzil = itemView.findViewById(R.id.textViewManzil);
        }
    }
}
