package com.codepath.kpu.simplist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.codepath.kpu.simplist.models.Task;

import java.util.ArrayList;

/**
 * Created by kpu on 1/23/16.
 */
public class TasksAdapter extends ArrayAdapter<Task> {
    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }
        // Lookup views for data population
        TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
        TextView tvData = (TextView)convertView.findViewById(R.id.tvDate);
        CheckBox cbComplete = (CheckBox)convertView.findViewById(R.id.cbComplete);

        // Populate the data into the template view using the data object
        tvName.setText(task.name);
        tvData.setText(getDateString(task.year, task.month, task.day));
        cbComplete.setChecked(task.complete);

        // Return the completed view to render on screen
        return convertView;
    }

    private String getDateString(int year, int month, int day) {
        // Account for jan -> 0
        return String.format("%d/%d/%d", month + 1, day, year);
    }

    public void updateTasks(ArrayList<Task> tasks) {
        this.clear();
        this.addAll(tasks);
        notifyDataSetChanged();
    }
}
