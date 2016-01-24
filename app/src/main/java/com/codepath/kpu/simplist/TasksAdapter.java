package com.codepath.kpu.simplist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);

        // Populate the data into the template view using the data object
        tvName.setText(task.name);

        // Return the completed view to render on screen
        return convertView;
    }

    public void updateTasks(ArrayList<Task> tasks) {
        this.clear();
        this.addAll(tasks);
        notifyDataSetChanged();
    }
}
