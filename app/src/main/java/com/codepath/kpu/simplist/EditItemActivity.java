package com.codepath.kpu.simplist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.activeandroid.query.Select;
import com.codepath.kpu.simplist.models.Task;

public class EditItemActivity extends AppCompatActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // Retrieve task from db
        long taskId = getIntent().getLongExtra("task_id", 0);
        task = new Select().from(Task.class).where("Id = ?", taskId).executeSingle();

        // Set text in view
        EditText etNewItem = (EditText)findViewById(R.id.etEditItem);
        etNewItem.setText(task.name);
    }

    public void onSave(View v) {
        EditText etName = (EditText)findViewById(R.id.etEditItem);

        task.name = etName.getText().toString().trim();
        task.save();

        this.finish();
    }
}
