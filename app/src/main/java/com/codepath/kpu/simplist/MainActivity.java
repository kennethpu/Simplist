package com.codepath.kpu.simplist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.codepath.kpu.simplist.models.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TasksAdapter tasksAdapter;
    ListView lvTasks;

    private final int REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvTasks = (ListView)findViewById(R.id.lvItems);
        tasksAdapter = new TasksAdapter(this, getTasks());
        lvTasks.setAdapter(tasksAdapter);
        setupListViewListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        tasksAdapter.updateTasks(getTasks());
    }

    private void setupListViewListener() {

        lvTasks.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Task task = tasksAdapter.getItem(position);
                        launchEditView(task);
                    }
                });

        lvTasks.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        // Remove task from view
                        Task task = tasksAdapter.getItem(position);
                        tasksAdapter.remove(task);
                        tasksAdapter.notifyDataSetChanged();

                        // Remove task from db
                        new Delete().from(Task.class).where("Id = ?", task.getId()).execute();
                        return true;
                    }
                });
    }

    private void launchEditView(Task task) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("task_id", task.getId());
        startActivity(i);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString().trim();

        // Get today's date
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Create new task (set due date initially to tomorrow), save to db
        Task task = new Task(itemText, year, month, day + 1, false);
        task.save();

        // Update view
        tasksAdapter.add(task);
        etNewItem.setText("");
    }

    private ArrayList<Task> getTasks() {
        // Query ActiveAndroid for list of data
        List<Task> queryResults = new Select()
                .from(Task.class)
                .orderBy("Id ASC")
                .execute();

        return (ArrayList<Task>)queryResults;
    }
}
